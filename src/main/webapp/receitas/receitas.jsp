<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="styles/index.css" />
    <title>Receitas</title>

    <style>
        .transaction-amount {
            font-weight: bold;
        }
        .transaction-debit {
            color: green;
        }
        .transaction-credit {
            color: green;
        }
        .accordion-body .transaction {
            display: block;
            padding: 15px;
            margin-bottom: 15px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            background-color: #f9f9f9;
        }
        .accordion-body .transaction:nth-child(even) {
            background-color: #f1f1f1;
        }
        .accordion-body .transaction:nth-child(odd) {
            background-color: #e0e0e0;
        }
        .transaction p {
            margin: 5px 0;
        }
        .transaction strong {
            font-size: 1.1rem;
        }
        .form-outline input, .form-outline select {
            margin-bottom: 15px;
            width: 380px;
        }
        .form-outline {
            width: 380px;
        }
    </style>
</head>
<body>
<div class="container">
    <header class="header">
        <img src="https://i.imgur.com/PFCD1Gr.png" onclick="window.location.href='home';" />
    </header>

    <div class="accordion" id="accordionExample">
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingReceitas">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseReceitas" aria-expanded="false" aria-controls="collapseReceitas">
                    Últimas Receitas
                </button>
            </h2>
            <div id="collapseReceitas" class="accordion-collapse collapse" aria-labelledby="headingReceitas">
                <div class="accordion-body">
                    <c:if test="${not empty transactions}">
                        <ul>
                            <c:forEach var="transaction" items="${transactions}">
                                <li class="transaction">
                                    <strong>${transaction.description}</strong><br>
                                    Valor:
                                    <span class="transaction-amount ${transaction.type == 'RECEITA' ? 'transaction-credit' : 'transaction-debit'}">
                                        ${transaction.amount} R$
                                    </span><br>
                                    Data: ${transaction.date}<br>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${empty transactions}">
                        <p><strong>Não há receitas cadastradas.</strong></p>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="accordion-item">
            <h2 class="accordion-header" id="headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                    Cadastrar Receita
                </button>
            </h2>
            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo">
                <div class="accordion-body">
                    <form action="<c:url value='/receitas'/>" method="post">
                        <div class="form-outline">
                            <input placeholder="Descrição" type="text" id="descricao" name="descricao" class="form-control form-control-lg" required/>
                        </div>
                        <div class="form-outline">
                            <input placeholder="Valor" type="number" id="amount" name="amount" class="form-control form-control-lg" required step="0.01" min="0"/>
                        </div>
                        <div class="form-outline">
                            <input placeholder="Data" type="date" id="date" name="date" class="form-control form-control-lg" required/>
                        </div>
                        <div class="form-outline">
                            <select id="type" name="type" class="form-control form-control-lg" required>
                                <option value="RECEITA">Receita</option>
                            </select>
                        </div>
                        <div class="d-flex justify-content-center align-items-center">
                            <button type="submit" class="btn btn-primary" style="width: 280px; margin: 0 auto; color: white;">
                                Cadastrar Receita
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="d-flex justify-content-center align-items-center">
                <button type="submit" class="btn btn-primary" style="width: 250px; color: white; margin-top: 20px" onclick="window.location.href='home'">
                    Voltar
                </button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
