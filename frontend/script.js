document.addEventListener("DOMContentLoaded", function () {
    let listaTarefas = JSON.parse(localStorage.getItem("tarefas")) || [];
    const formulario = document.getElementById("todo-form");
    const inputIdTarefa = document.getElementById("task-id");
    const inputNomeTarefa = document.getElementById("task-name");
    const inputDescricaoTarefa = document.getElementById("task-description");
    const inputDataTarefa = document.getElementById("task-date");
    const inputPrioridadeTarefa = document.getElementById("task-priority");
    const inputCategoriaTarefa = document.getElementById("task-category");
    const inputStatusTarefa = document.getElementById("task-status");
    const tabelaTarefas = document.getElementById("tasks-container");

    const filtroStatus = document.getElementById("filter-status");
    const filtroPrioridade = document.getElementById("filter-priority");
    const filtroCategoria = document.getElementById("filter-category");

    function salvarTarefasLocalStorage() {
        localStorage.setItem("tarefas", JSON.stringify(listaTarefas));
    }

    function limparFormulario() {
        inputIdTarefa.value = "";
        inputNomeTarefa.value = "";
        inputDescricaoTarefa.value = "";
        inputDataTarefa.value = "";
        inputPrioridadeTarefa.value = "1";
        inputCategoriaTarefa.value = "";
        inputStatusTarefa.value = "TODO";
    }

    function listarTarefas() {
        tabelaTarefas.innerHTML = "";
        const statusFiltro = filtroStatus.value;
        const prioridadeFiltro = filtroPrioridade.value;
        const categoriaFiltro = filtroCategoria.value.trim().toLowerCase();

        const tarefasFiltradas = listaTarefas.filter(tarefa => {
            const statusMatch = statusFiltro === "all" || tarefa.status === statusFiltro;
            const prioridadeMatch = prioridadeFiltro === "all" || tarefa.prioridade === prioridadeFiltro;
            const categoriaMatch = categoriaFiltro === "" || tarefa.categoria.toLowerCase().includes(categoriaFiltro);

            return statusMatch && prioridadeMatch && categoriaMatch;
        });

        tarefasFiltradas.forEach((tarefa) => {
            const linha = document.createElement("tr");
            linha.innerHTML = `
                <td>${tarefa.nome}</td>
                <td>${tarefa.descricao}</td>
                <td>${tarefa.data}</td>
                <td>${tarefa.prioridade}</td>
                <td>${tarefa.categoria}</td>
                <td>${tarefa.status}</td>
                <td class="action-buttons">
                    <button class="edit-btn" onclick="editarTarefa(${tarefa.id})">✏️</button>
                    <button class="delete-btn" onclick="removerTarefa(${tarefa.id})">🗑️</button>
                </td>
            `;
            tabelaTarefas.appendChild(linha);
        });
    }

    function adicionarOuEditarTarefa(event) {
        event.preventDefault();
        const id = inputIdTarefa.value ? Number(inputIdTarefa.value) : Date.now();
        const nome = inputNomeTarefa.value.trim();
        const descricao = inputDescricaoTarefa.value.trim();
        const data = inputDataTarefa.value;
        const prioridade = inputPrioridadeTarefa.value;
        const categoria = inputCategoriaTarefa.value.trim();
        const status = inputStatusTarefa.value;

        if (!nome || !descricao || !data || !prioridade || !categoria || !status) {
            alert("Por favor, preencha todos os campos!");
            return;
        }

        const novaTarefa = { id, nome, descricao, data, prioridade, categoria, status };
        const index = listaTarefas.findIndex(tarefa => tarefa.id === id);
        
        if (index !== -1) {
            listaTarefas[index] = novaTarefa;
        } else {
            listaTarefas.push(novaTarefa);
        }
        
        salvarTarefasLocalStorage();
        limparFormulario();
        listarTarefas();
    }

    window.editarTarefa = function (id) {
        const tarefa = listaTarefas.find(tarefa => tarefa.id === id);
        if (!tarefa) return;
        
        inputIdTarefa.value = tarefa.id;
        inputNomeTarefa.value = tarefa.nome;
        inputDescricaoTarefa.value = tarefa.descricao;
        inputDataTarefa.value = tarefa.data;
        inputPrioridadeTarefa.value = tarefa.prioridade;
        inputCategoriaTarefa.value = tarefa.categoria;
        inputStatusTarefa.value = tarefa.status;
    };

    window.removerTarefa = function (id) {
        listaTarefas = listaTarefas.filter(tarefa => tarefa.id !== id);
        salvarTarefasLocalStorage();
        listarTarefas();
    };

    formulario.addEventListener("submit", adicionarOuEditarTarefa);
    filtroStatus.addEventListener("change", listarTarefas);
    filtroPrioridade.addEventListener("change", listarTarefas);
    filtroCategoria.addEventListener("input", listarTarefas);
    
    listarTarefas();
});
