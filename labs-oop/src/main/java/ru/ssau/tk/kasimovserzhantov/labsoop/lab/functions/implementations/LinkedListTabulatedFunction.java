package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.abstractclasses.AbstractTabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.Insertable;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.Removable;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {

    private int count;
    private Node head;

    private static class Node {
        Node prev;
        Node next;
        double x;
        double y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Array's length should be at least 2");
        }

        if (xValues.length != yValues.length) {
            throw new IllegalArgumentException("Arrays must be the same length");
        }

        for (int i = 0; i < xValues.length; ++i) {
            if (i > 0 && xValues[i] < xValues[i - 1])
                throw new IllegalArgumentException("Array must be sorted in increasing order");

            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Count should be at least 2");
        }

        if (source == null) {
            throw new IllegalArgumentException("Source can't be a null");
        }

        if (xFrom > xTo) {
            double tmp = xTo;
            xTo = xFrom;
            xFrom = tmp;
        }

        if (xFrom == xTo) {
            for (int i = 0; i < count; ++i) {
                addNode(xFrom, source.apply(xFrom));
            }
        } else {
            double step = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; ++i) {
                double x = xFrom + step * i;
                double y = source.apply(x);
                addNode(x, y);
            }
        }
    }

    private void isIndexValid(int index) {
        if (index < 0 || index >= getCount())
            throw new IndexOutOfBoundsException();
    }

    private void addNode(double x, double y) {
        Node newNode = new Node(x, y);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Node last = head.prev;
            last.next = newNode;
            newNode.prev = last;
            newNode.next = head;
            head.prev = newNode;
        }

        count++;
    }

    private Node getNode(int index) {
        isIndexValid(index);

        Node curr;
        if (index < count / 2) {
            curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        } else {
            curr = head.prev;
            for (int i = count - 1; i > index; i--) {
                curr = curr.prev;
            }
        }
        return curr;
    }

    private Node floorNodeOfX(double x) {
        Node curr = head;

        if (curr == null || x < head.x) {
            return null;
        }

        while (curr.next != head && curr.next.x < x) {
            curr = curr.next;
        }

        return curr;
    }

    private double extrapolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) / (rightX - leftX) * (x - leftX);
    }

    @Override
    public double apply(double x) {
        if (count == 0) {
            throw new IllegalArgumentException("Function is empty");
        }

        if (count == 1) {
            if (x < leftBound() || x > rightBound()) {
                throw new IllegalArgumentException("x is out of bounds");
            }
        }

        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        }

        Node node = floorNodeOfX(x);

        if (node == null) {
            throw new IllegalArgumentException("x is out of bounds");
        }

        if (node.x == x) {
            return node.y;
        }

        Node nextNode = node.next;
        return extrapolate(x, node.x, nextNode.x, node.y, nextNode.y);
    }

    @Override
    public void insert(double x, double y) {
        if (Double.isNaN(x) || Double.isNaN(y))
            throw new IllegalArgumentException("X and Y should be a valid number");

        if (count == 0) {
            addNode(x, y);
            return;
        }

        int indexOfX = indexOfX(x);
        if (indexOfX != -1) {
            setY(indexOfX, y);
        } else {
            Node newNode = new Node(x, y);
            Node current = head;

            while (current != null && current.x < x) {
                current = current.next;
            }

            if (current == null) {
                Node last = head.prev;
                last.next = newNode;
                newNode.prev = last;
                newNode.next = head;
                head.prev = newNode;
            } else if (current == head) {
                newNode.next = head;
                newNode.prev = head.prev;
                head.prev.next = newNode;
                head.prev = newNode;
                head = newNode;
            } else {
                newNode.prev = current.prev;
                newNode.next = current;

                if (current.prev != null) {
                    current.prev.next = newNode;
                }
                current.prev = newNode;
            }

            count++;
        }
    }

    @Override
    public void remove(int index) {
        isIndexValid(index);

        Node removableNode = getNode(index);
        if (removableNode == head) {
            if (count == 1) {
                head = null;
            } else {
                head = head.next;
                head.prev = head.prev.prev;
                head.prev.next = head;
            }
        } else {
            removableNode.prev.next = removableNode.next;
            if (removableNode.next != null) {
                removableNode.next.prev = removableNode.prev;
            }
        }

        count--;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node curr = head;
        for (int i = 0; i < count; ++i) {
            if (curr.x == x)
                return i;
            curr = curr.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node curr = head;
        for (int i = 0; i < count; ++i) {
            if (curr.y == y)
                return i;
            curr = curr.next;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < leftBound())
            throw new IllegalArgumentException("x is out of bounds");

        Node node = head;
        for (int i = 0; i < count; i++) {
            node = node.next;
            if (x < node.x) return i;
        }
        return 0;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return count == 1 ? head.y : extrapolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return count == 1 ? head.y : extrapolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (floorIndex == count - 1) {
            return head.prev.y;
        }
        Node leftNode = getNode(floorIndex);
        Node rightNode = leftNode.next;
        return interpolate(x, leftNode.x, rightNode.x, leftNode.y, rightNode.y);
    }

}
