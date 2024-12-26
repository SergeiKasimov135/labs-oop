function openModal(target,redirectTarget) {
    document.getElementById("modalOverlay").style.display = 'block';
    fetch(`/arrayFunc/createForm?target=${target}&redirectTarget=${redirectTarget}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Ошибка загрузки модального окна');
            }
            return response.text();
        })
        .then(html => {
            document.getElementById('modalContent').innerHTML = html;
            document.getElementById('modal').style.display = 'block';
        })
        .catch(error => {
            console.error(error);
        });
}

function closeModal() {
    document.getElementById('modal').style.display = 'none';
    document.getElementById("modalOverlay").style.display = 'none';
}

function submitForm(event) {
    const form = event.target;

    const target = form.querySelector('input[name="target"]').value
    const redirectTarget = form.querySelector('input[name="redirectTarget"]').value;

    event.preventDefault();
    const formData = new FormData(form);

    const url = `/arrayFunc/generateTable`;

    fetch(url, {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(data => {
                    throw new Error(data.error || "Ошибка при генерации таблицы.");
                });
            }
            return response.text();
        })
        .then(html => {
            document.getElementById('modalContent').innerHTML = html;
        })
        .catch(error => {

            const errorForm = document.getElementById('errorForm');
            if (errorForm) {
                errorForm.style.display = 'block';
                document.getElementById('errorMessage').textContent = error.message;
            }
        });
}

let modalTarget = '';
let selectFunctionId = null;

function openFunctionList(target) {
    modalTarget = target;
    document.getElementById('modalFunction').style.display = 'block';
    document.getElementById("modalOverlay").style.display = 'block';
}

function closeFunctionList() {
    document.getElementById('modalFunction').style.display = 'none';
    document.getElementById("modalOverlay").style.display = 'none';
}

function selectFunction(row, id, target) {

    console.log(row, id, target);
    console.log(target);
    // Снимаем выделение со всех строк
    const rows = document.querySelectorAll('.function-row');
    rows.forEach(r => r.classList.remove('selected'));

    // Выделяем выбранную строку
    row.classList.add('selected');

    selectFunctionId = id;

    console.log(selectFunctionId);
}

function loadFunction() {
    if (!selectFunctionId || !modalTarget) {
        console.error("Выберите функцию и цель перед загрузкой.");
        return;
    }

    const url = `/memory/load?target=${encodeURIComponent(modalTarget)}&id=${encodeURIComponent(selectFunctionId)}`;

    fetch(url, { method: 'POST' })
        .then(response => {
            if (!response.ok) {
                return response.json();
            }
            return null;
        })
        .then(data => {
            if (data && data.error) {
                const errorForm = document.getElementById('errorForm');
                if (errorForm) {
                    closeFunctionList();
                    errorForm.style.display = 'block';
                    document.getElementById('errorMessage').textContent = data.error;
                }
            } else {
                closeFunctionList();
                window.location.reload();
            }
        })
        .catch(error => {
            console.error('Ошибка при загрузке функции');
        });
}


let saveTarget = '';
function openSave(target){
    saveTarget = target;
    document.getElementById('modalSave').style.display = 'block';
    document.getElementById('modalOverlay').style.display = 'block';
}

function closeSave() {
    document.getElementById('modalSave').style.display = 'none';
    document.getElementById('modalOverlay').style.display = 'none';
}

function saveFunction(event) {

    event.preventDefault();
    const funcName = document.getElementById('funcName').value;

    fetch(`/memory/save?target=${encodeURIComponent(saveTarget)}&funcName=${encodeURIComponent(funcName)}`, {
        method: 'POST'
    })
    window.location.reload();
}

function openAnother(redirectTarget,target){
    const modalContent = document.getElementById('modalContentAnother');
    const modal = document.getElementById('modalAnother');
    const overlay = document.getElementById('modalOverlay');

    fetch(`/tabulated-function-mathfunc/modal?redirectTarget=${encodeURIComponent(redirectTarget)}&target=${encodeURIComponent(target)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Ошибка загрузки модального окна');
            }
            return response.text();
        })
        .then(html => {
            modalContent.innerHTML = html;

            modal.style.display = 'block';
            overlay.style.display = 'block';
        })
        .catch(error => {
            console.error(error);
        });
}

function closeAnother(){
    document.getElementById('modalAnother').style.display = 'none';
    document.getElementById('modalOverlay').style.display ='none';
}

function close(){
    document.getElementById('modal').style.display = 'none';
}

function create() {
    const form = document.getElementById('createFunctionForm');
    const target = document.getElementById('targetField').value;
    const redirectTarget = document.getElementById('redirectTargetField').value;

    // Собираем значения x и y из таблицы
    const xValues = [];
    const yValues = [];
    const rows = form.querySelectorAll('table tr');

    rows.forEach(row => {
        const xInput = row.querySelector('input[name="x"]');
        const yInput = row.querySelector('input[name="y"]');
        if (xInput && yInput) {
            xValues.push(parseFloat(xInput.value) + 0.01); // Преобразуем в числа с добавлением 0.01
            yValues.push(parseFloat(yInput.value) + 0.01); // Преобразуем в числа с добавлением 0.01
        }
    });

    const data = {
        target: target,
        redirectTarget: redirectTarget,
        x: xValues,
        y: yValues
    };

    // Отправляем запрос с использованием fetch
    fetch('/tabulated-operations/createFunction', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',  // Указываем, что отправляем JSON
        },
        body: JSON.stringify(data),  // Преобразуем объект данных в строку JSON
    })
        .then(response => {
            if (response.ok) {
                window.location.reload();
            } else {
                return response.json(); // Обрабатываем ошибку, если есть
            }
        })
        .then(data => {
            if (data && data.error) {
                // Обрабатываем ошибку
                console.error('123123')
                const errorForm = document.getElementById('errorForm');
                if (errorForm) {
                    errorForm.style.display = 'block';
                    document.getElementById('errorMessage').textContent = data.error;
                }
            }
        })
        .catch(error => {
            console.error('Ошибка при загрузке функции', error);
        });

}

let editTarget = '';
function openEdit(target){
    editTarget = target;
    document.getElementById('modalEdit').style.display = 'block';
    document.getElementById('modalOverlay').style.display = 'block';
}

function closeEdit(){
    document.getElementById('modalEdit').style.display = 'none';
    document.getElementById('modalOverlay').style.display = 'none';
}

function editFunc(event){
    event.preventDefault();
    const x = document.getElementById('xValue').value;
    const y = document.getElementById('yValue').value;

    console.log(x);
    console.log(y);

    fetch(`/editing/edit?target=${encodeURIComponent(editTarget)}&x=${encodeURIComponent(x)}&y=${encodeURIComponent(y)}`, {
        method: 'POST'
    })
        .then(response => {
            if (!response.ok) {
                return response.json();
            }
            return null;
        })
        .then(data => {
            if (data && data.error) {
                const errorForm = document.getElementById('errorForm');
                if (errorForm) {
                    errorForm.style.display = 'block';
                    document.getElementById('errorMessage').textContent = data.error;
                }
            } else {
                window.location.reload();
            }
        })
        .catch(error => {
            console.error('Ошибка при загрузке функции');
        });
}
let removeTarget = '';
let redirectRemoveTarget = '';
function openRemove(target,redirectTarget){
    removeTarget = target;
    redirectRemoveTarget = redirectTarget;
    document.getElementById('modalRemove').style.display = 'block';
    document.getElementById('modalOverlay').style.display = 'block';
}

function removeFunc(event){

    event.preventDefault();
    const x = document.getElementById('xToRemove').value;
    console.log(x);

    fetch(`/editing/remove?target=${encodeURIComponent(removeTarget)}&x=${encodeURIComponent(x)}&redirectTarget=${encodeURIComponent(redirectRemoveTarget)}`, {
        method: 'POST'
    })
        .then(response => {
            if (!response.ok) {
                console.log('prikol')
                return response.json();
            }
            return null;
        })
        .then(data => {
            if (data && data.error) {
                const errorForm = document.getElementById('errorForm');
                if (errorForm) {
                    closeRemove()
                    errorForm.style.display = 'block';
                    document.getElementById('errorMessage').textContent = data.error;
                }
            } else {
                closeRemove()
                window.location.reload();
                console.log('good')
            }
        })
        .catch(error => {
            console.error('Ошибка при загрузке функции');
        });

}

function closeRemove(){
    document.getElementById('modalRemove').style.display = 'none';
    document.getElementById('modalOverlay').style.display = 'none';
}