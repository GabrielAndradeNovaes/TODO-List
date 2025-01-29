import java.util.*;

class Tarefa {
    private String nome;
    private String descricao;
    private Date data;
    private int prioridade;
    private String categoria;
    private String status;

    public Tarefa(String nome, String descricao, Date data, int prioridade, String categoria, String status) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = status;
    }

    public String getnome() {
        return nome;
    }

    public String getdescricao() {
        return descricao;
    }

    public Date getdata() {
        return data;
    }

    public int getprioridade() {
        return prioridade;
    }

    public String getcategoria() {
        return categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", prioridade=" + prioridade +
                ", categoria='" + categoria + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

class GerenciadorDeTarefas {
    private List<Tarefa> Tarefas;

    public GerenciadorDeTarefas() {
        Tarefas = new ArrayList<>();
    }

    public void addTarefa(Tarefa tarefa) {
        Tarefas.add(tarefa);
        Tarefas.sort(Comparator.comparingInt(Tarefa::getprioridade));
    }

    public void deleteTarefa(String nome) {
        Iterator<Tarefa> iterator = Tarefas.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getnome().equalsIgnoreCase(nome)) {
                iterator.remove();
                break;
            }
        }
    }

    public void listTarefas(String TipoDoFiltro, String ValorDoFiltro) {
        List<Tarefa> TarefasFiltradas = new ArrayList<>();

        for (Tarefa Tarefa : Tarefas) {
            switch (TipoDoFiltro.toLowerCase()) {
                case "categoria":
                    if (Tarefa.getcategoria().equalsIgnoreCase(ValorDoFiltro)) {
                        TarefasFiltradas.add(Tarefa);
                    }
                    break;
                case "prioridade":
                    int prioridadeFilter = Integer.parseInt(ValorDoFiltro);
                    if (Tarefa.getprioridade() == prioridadeFilter) {
                        TarefasFiltradas.add(Tarefa);
                    }
                    break;
                case "status":
                    if (Tarefa.getStatus().equalsIgnoreCase(ValorDoFiltro)) {
                        TarefasFiltradas.add(Tarefa);
                    }
                    break;
                default:
                    System.out.println("Tipo invalido");
                    return;
            }
        }

        for (Tarefa Tarefa : TarefasFiltradas) {
            System.out.println(Tarefa);
        }
    }

    public void displaySummary() {
        int todoCount = 0, doingCount = 0, doneCount = 0;

        for (Tarefa Tarefa : Tarefas) {
            switch (Tarefa.getStatus().toLowerCase()) {
                case "todo":
                    todoCount++;
                    break;
                case "doing":
                    doingCount++;
                    break;
                case "done":
                    doneCount++;
                    break;
            }
        }

        System.out.println("Total:");
        System.out.println("To Do: " + todoCount);
        System.out.println("Doing: " + doingCount);
        System.out.println("Done: " + doneCount);
    }
}

public class TodoListApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorDeTarefas GerenciadorDeTarefas = new GerenciadorDeTarefas();

        while (true) {
            System.out.println("\nTODO List Menu:");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Deletar Tarefa");
            System.out.println("3. Listar Tarefas");
            System.out.println("4. Mostrar Total");
            System.out.println("5. sair");

            System.out.print("Escolha uma opcao: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    System.out.print("Ensira o nome da Tarefa: ");
                    String nome = scanner.nextLine();
                    System.out.print("Ensira a descricao: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Ensira a data (yyyy-MM-dd): ");
                    String dateInput = scanner.nextLine();
                    System.out.print("Ensira a prioridade (1-5): ");
                    int prioridade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ensira a categoria: ");
                    String categoria = scanner.nextLine();
                    System.out.print("Ensira o status (todo/doing/done): ");
                    String status = scanner.nextLine();

                    try {
                        Date data = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateInput);
                        Tarefa Tarefa = new Tarefa(nome, descricao, data, prioridade, categoria, status);
                        GerenciadorDeTarefas.addTarefa(Tarefa);
                        System.out.println("Tarefa Adicionada com sucesso");
                    } catch (Exception e) {
                        System.out.println("formato invalido");
                    }
                    break;

                case 2:
                    System.out.print("Ensira o nome da tarefa a ser deletada: ");
                    String deletenome = scanner.nextLine();
                    GerenciadorDeTarefas.deleteTarefa(deletenome);
                    System.out.println("Tarefa deletada com sucesso!");
                    break;

                case 3:
                    System.out.print("Filtrar por (categoria/prioridade/status): ");
                    String TipoDoFiltro = scanner.nextLine();
                    System.out.print("Ensira valor do filtro: ");
                    String ValorDoFiltro = scanner.nextLine();
                    GerenciadorDeTarefas.listTarefas(TipoDoFiltro, ValorDoFiltro);
                    break;

                case 4:
                    GerenciadorDeTarefas.displaySummary();
                    break;

                case 5:
                    System.out.println("saindo");
                    return;

                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }
}
