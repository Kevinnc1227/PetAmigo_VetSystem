# 🐾 PetAmigo VetSystem

> Sistema de Gerenciamento Veterinário desenvolvido para a disciplina de **Programação Avançada (184987)** — AV2 — Uniasselvi.

---

## 👥 Equipe e Particionamento das Responsabilidades

O projeto foi dividido de forma descentralizada entre os membros da equipe, conforme exigido pela avaliação. Cada membro possui sua própria branch no repositório.

| Cor | Membro | Responsabilidade |
|-----|--------|-----------------|
| 🔵 **Azul** | Kevin | **Infraestrutura** — `Pessoa`, `Cliente`, `Veterinario`, `Endereco`, `Telefone` + DAOs + GUIs |
| 🟢 **Verde** | Leonardo | **Domínio e Exceções** — `Animal`, `Prontuario`, `TipoAnimal`, `CustomizeException` + DAOs + GUIs |
| 🟡 **Amarelo** | Gabriel Lucas | **Operações e Financeiro** — `Consulta`, `Pagamento`, `Data`, `Hora`, `StatusPagamento` + DAOs + GUIs |

> **Importante:** Cada trecho de código possui comentário de autoria (`// Autor: [Nome]`) e os commits foram feitos individualmente nas branches de cada membro.

---

## 📊 Diagrama UML de Classes

O diagrama foi elaborado no [draw.io](https://app.diagrams.net/) e se encontra no arquivo `docs/diagrama_classes_petamigo.drawio`.

![Diagrama de Classes](./docs/diagrama_classes_petamigo.png)

### Principais relacionamentos:
- `Cliente` e `Veterinario` **herdam** de `Pessoa` (herança)
- `Pessoa` **compõe** `Endereco` e `Telefone` (composição)
- `Cliente` **agrega** `Animal` — um cliente pode ter 0 ou mais pets (agregação `0..*`)
- `Consulta` **associa** `Animal`, `Veterinario` e `Pagamento`
- `Animal` lança `CustomizeException`, que **estende** `Exception`

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Uso |
|------------|-----|
| ☕ Java 8 | Linguagem principal |
| 🐬 MySQL | Banco de dados relacional (`db_petamigo`) |
| 🖥️ Eclipse IDE 2025-03 | Ambiente de desenvolvimento |
| 📐 Draw.io | Modelagem UML |
| 🐙 Git / GitHub | Versionamento com branches individuais |

---

## 🗂️ Estrutura do Projeto

```
PetAmigo_VetSystem/
│
├── src/
│   ├── model/          # Classes de domínio e DAOs (JDBC)
│   │   ├── Pessoa.java
│   │   ├── Cliente.java
│   │   ├── ClienteDAO.java
│   │   ├── Veterinario.java
│   │   ├── VeterinarioDAO.java
│   │   ├── Endereco.java
│   │   ├── Telefone.java
│   │   ├── Animal.java
│   │   ├── AnimalDAO.java
│   │   ├── Prontuario.java
│   │   ├── ProntuarioDAO.java
│   │   ├── TipoAnimal.java
│   │   ├── Consulta.java
│   │   ├── ConsultaDAO.java
│   │   ├── Pagamento.java
│   │   ├── PagamentoDAO.java
│   │   ├── Data.java
│   │   ├── Hora.java
│   │   ├── StatusPagamento.java
│   │   ├── BD.java
│   │   ├── OperacaoBD.java
│   │   └── TipoOperacaoBD.java
│   │
│   └── view/           # Interfaces gráficas (Swing)
│       ├── Main.java
│       ├── TelaPrincipal.java
│       ├── TelaCadastroCliente.java
│       ├── TelaCadastroAnimal.java
│       ├── TelaCadastroVeterinario.java
│       ├── TelaAgendamentoConsulta.java
│       ├── TelaConsultaAgendamentos.java
│       └── TelaProntuario.java
│
├── lib/
│   └── mysql-connector-j-8.0.33.jar
│
├── docs/
│   ├── script_db.sql       # Criação das tabelas
│   └── script_dados.sql    # Dados iniciais de teste
│
├── Diagrama_De_Classes.drawio
├── Diagrama_De_Classes.png
└── README.md
```

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- Java 8 (JDK 8+)
- MySQL Server rodando localmente
- Eclipse IDE 2025-03
- Conector JDBC MySQL (`lib/mysql-connector-j-8.0.33.jar`) no Build Path

### Passo a passo

**1. Clone o repositório:**
```bash
git clone https://github.com/Kevinnc1227/PetAmigo_VetSystem.git
```

**2. Configure o banco de dados:**

Abra o MySQL e execute os scripts na ordem:
```bash
mysql -u root -p < docs/script_db.sql
mysql -u root -p PetAmigo_VetSystem < docs/script_dados.sql
```

**3. Importe no Eclipse:**
```
File → Import → General → Existing Projects into Workspace
```

**4. Adicione o driver MySQL ao Build Path:**
```
Clique direito no projeto → Build Path → Configure Build Path → Libraries → Add JARs... → selecione lib/mysql-connector-j-8.0.33.jar
```

**5. Ajuste as credenciais do banco** em `src/model/BD.java`:
```java
private final String URL   = "jdbc:mysql://localhost:3306/PetAmigo_VetSystem";
private final String LOGIN = "root";
private final String SENHA = "sua_senha_aqui";
```

**6. Execute a classe principal:**
```
Run → view.Main
```

---

## 🌿 Branches do Repositório

| Branch | Responsável | Conteúdo |
|--------|-------------|----------|
| `main` | Equipe | Código integrado e estável |
| `feature/infraestrutura` | [Seu Nome] 🔵 | Pessoa, Cliente, Veterinario, Endereco, Telefone |
| `feature/dominio` | Jaime Junior 🟢 | Animal, Prontuario, TipoAnimal, CustomizeException |
| `feature/operacoes` | [Nome] 🟡 | Consulta, Pagamento, Data, Hora |

---

## 📚 Referência Bibliográfica

FURGERI, Sérgio. **Java 8 - ensino didático: desenvolvimento e implementação de aplicações**. São Paulo: Érica, 2015.
Disponível em: <https://app.minhabiblioteca.com.br/reader/books/9788536519340>. Acesso em: 10 fev. 2025.

- Capítulo 8 e 9 → Interface gráfica com Swing (JFrame, JPanel, eventos)
- Capítulo 12 → Persistência com JDBC e MySQL

---

## 📝 Licença

Projeto acadêmico desenvolvido para fins educacionais — Uniasselvi 2025.