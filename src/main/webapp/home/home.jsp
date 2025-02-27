<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="model.Transaction" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="styles/index.css" />
    <title>Home</title>
    <style>
        .transaction-amount {
            font-weight: bold;
        }
        .transaction-debit {
            color: red;
        }
        .transaction-credit {
            color: green;
        }

        .accordion-body ul {
            padding-left: 0;
            list-style-type: none;
            margin: 0;
        }

        .accordion-body li {
            display: block;
            width: 380px;
            height: 130px;
            margin-bottom: 15px;
            padding: 10px;
            border-radius: 5px;
        }

        .accordion-body li:nth-child(even) {
            background-color: #f1f1f1;
        }

        .accordion-body li:nth-child(odd) {
            background-color: #e0e0e0;
        }

        .accordion-body li strong {
            font-size: 1.1rem;
        }

        .btn-navigation {
            width: 200px;
            text-align: center;
            background-color: #ff8200;
            border-color: #ff8200;
            border-radius: 12px;
            display: flex;
            justify-content: space-evenly;
            align-items: center;
            font-size: 16px;
            text-decoration: none;
            padding-right: 20px;
            margin-top: 20px;
        }

        .btn-navigation i {
            padding-left: -5px;
        }
    </style>
</head>
<body>
<div class="container">
    <header class="header d-flex justify-content-between align-items-center">
        <img src="https://i.imgur.com/PFCD1Gr.png" alt="Logo" />
        <i onclick="logout()" class="fas fa-power-off" style="font-size: 35px;color:#ff8200;"></i>
    </header>

    <%
        User user = (User) request.getAttribute("user");
        String firstName = (user != null) ? user.getFirstName() : "Usuário";
    %>

    <div class="alert alert-info" role="alert">
        Olá, <%= firstName %>!
    </div>

    <div class="accordion" id="accordionExample">
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingOne">
                <button style="color: white" class="accordion-button" type="button" id="toggleSaldoButton" onclick="toggleSaldo()">
                    Saldo Atual
                    <span id="saldoAtual" data-saldo="R$ <%= (request.getAttribute("balance") != null) ? String.format("%.2f", (BigDecimal) request.getAttribute("balance")) : "0.00" %>">
                        ********
                    </span>
                    <i id="eyeIcon" class="fas fa-eye"></i>
                </button>
            </h2>
        </div>
    </div>

    <div class="accordion" id="accordionTransacoes">
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingTransacoes">
                <button style="color: white" class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTransacoes" aria-expanded="true" aria-controls="collapseTransacoes">
                    Últimas Transações
                </button>
            </h2>
            <div id="collapseTransacoes" class="accordion-collapse collapse" aria-labelledby="headingTransacoes" data-bs-parent="#accordionTransacoes">
                <div class="accordion-body">
                    <ul>
                        <%
                            List<Transaction> transacoes = (List<Transaction>) request.getAttribute("transacoes");
                            if (transacoes != null) {
                                transacoes.sort((t1, t2) -> t2.getDate().compareTo(t1.getDate()));
                                for (Transaction transacao : transacoes) {
                        %>
                        <li>
                            <strong><%= transacao.getDescription() %></strong><br>
                            Valor:
                            <span class="transaction-amount
                                <%= transacao.getType().equals("D") ? "transaction-debit" : "transaction-credit" %>">
                                R$ <%= transacao.getAmount().setScale(2) %>
                            </span><br>
                            Data: <%= transacao.getDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) %><br>
                            Tipo: <%= transacao.getType() %>
                        </li>
                        <%
                                }
                            }
                        %>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="d-flex justify-content-center align-items-center">
        <button type="submit" class="btn btn-primary" style="width: 180px;color:white;margin-top: 20px" onclick="window.location.href='receitas'">
            Cadastrar Receita
        </button>
    </div>
    <div class="d-flex justify-content-center align-items-center mt-2">
        <button type="submit" class="btn btn-primary" style="width: 180px;color:white;margin-top: 20px" onclick="window.location.href='despesas'">
            Cadastrar Despesa
        </button>
    </div>
</div>

<script>
    function toggleSaldo() {
        var saldoElement = document.getElementById('saldoAtual');
        var eyeIcon = document.getElementById('eyeIcon');

        if (saldoElement.textContent === '********') {
            saldoElement.textContent = saldoElement.getAttribute('data-saldo');
            eyeIcon.classList.remove('fa-eye');
            eyeIcon.classList.add('fa-eye-slash');
        } else {
            saldoElement.textContent = '********';
            eyeIcon.classList.remove('fa-eye-slash');
            eyeIcon.classList.add('fa-eye');
        }
    }

    function logout() {
        if (confirm("Você deseja deslogar?")) {
            fetch('<%= request.getContextPath() %>/home', {
                method: 'POST',
            }).then(() => {
                window.location.href = '<%= request.getContextPath() %>/login';
            }).catch(error => console.error('Erro ao fazer logout:', error));
        }
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
