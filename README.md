# Gerenciador de Tarefas (TODO List)

## Projeto de Gabriel Andrade Novaes

Este projeto é um **Gerenciador de Tarefas (TODO List)** desenvolvido em **Java**, permitindo que os usuários adicionem, removam, filtrem e visualizem tarefas diretamente pelo terminal.

## 🚀 Funcionalidades
- **Adicionar tarefas** com nome, descrição, data, prioridade, categoria e status.
- **Remover tarefas** pelo nome.
- **Listar tarefas** filtrando por categoria, prioridade ou status.
- **Exibir resumo** das tarefas cadastradas (quantidade por status).
- **Ordenação automática** das tarefas por prioridade.

## 📌 Tecnologias Utilizadas
- **Java 11+** - Linguagem principal do projeto.
- **Scanner (java.util.Scanner)** - Entrada de dados pelo terminal.
- **ArrayList (java.util.ArrayList)** - Armazenamento das tarefas.
- **Date (java.util.Date)** - Manipulação de datas.
- **Comparator (java.util.Comparator)** - Ordenação das tarefas.

## 🛠️ Como Executar o Programa

### 1️⃣ Compilar o código-fonte
Abra o terminal e navegue até o diretório do projeto onde está localizado o arquivo `TodoListApp.java` por exemplo, se estiver salvo em `C:\TODO-List\TODO-List\src`, tera que percorrer o caminho ate la . Em seguida, execute:
```bash
javac TodoListApp.java
```

### 2️⃣ Executar o programa
Após a compilação, execute o programa com:
```bash
java TodoListApp
```

## 📝 Comentários sobre a Solução
- O uso de **ArrayList** permite manipulação dinâmica das tarefas.
- A **ordenação automática por prioridade** melhora a usabilidade.
- Implementações futuras podem incluir **persistência de dados** em arquivos ou banco de dados.
- Validações adicionais poderiam ser incluídas para evitar entradas inválidas.

## 📌 Exemplo de Uso
```bash
TODO List Menu:
1. Adicionar Tarefa
2. Deletar Tarefa
3. Listar Tarefas
4. Mostrar Total
5. Sair
Escolha uma opcao: 1
Ensira o nome da Tarefa: Estudar Java
Ensira a descricao: Revisar classes e objetos
Ensira a data (dd/MM/yyyy): 30/01/2025
Ensira a prioridade (1-Alta, 2-Média, 3-Baixa): 1
Ensira a categoria: Estudos
Ensira o status (ToDo, Doing, Done): ToDo
Tarefa adicionada com sucesso!
```


