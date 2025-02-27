<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
    <link rel="stylesheet" href="styles/index.css" />
    <title>Money Matters - Criar Conta Bancária</title>

    <!-- Inputmask Library -->
    <script src="https://cdn.jsdelivr.net/npm/inputmask@5.0.7/dist/inputmask.min.js"></script>
</head>

<body>
<div class="container">
    <img src="https://i.imgur.com/vXWhCCU.png" alt="logo-fundo-branco" class="mb-4" />

    <form method="POST" action="acc">
        <c:if test="${not empty errorMessage}">
            <script type="text/javascript">
                alert("${errorMessage}");
            </script>
        </c:if>

        <div data-mdb-input-init class="form-outline mb-2">
            <input id="agency" name="agency" placeholder="Agência" type="text" class="form-control" required />
        </div>

        <div data-mdb-input-init class="form-outline mb-2">
            <input id="accNum" name="accNum" placeholder="Número da Conta" type="text" class="form-control" required />
        </div>

        <div data-mdb-input-init class="form-outline mb-2">
            <input name="balance" placeholder="Saldo Inicial" type="number" step="0.01" class="form-control" required />
        </div>

        <input name="userId" type="hidden" value="${userId}" />

        <div data-mdb-input-init class="form-outline mb-2">
            <select name="bankId" class="form-control" required>
                <c:forEach var="bank" items="${banks}">
                    <option value="${bank.bkId}">${bank.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="text-center">
            <button onclick="window.location.href='home'" style="width: 150px" type="submit" class="btn btn-primary btn-block mb-4"  >
                CRIAR CONTA
            </button>
        </div>

        <div class="text-center second-container">
            <p class="mt-sm"><a href="acc">Já possui uma conta?</a></p>
        </div>
    </form>
</div>

<!-- Script para aplicar a máscara -->
<script>
    // Máscara para o campo Agência
    var agencyInput = document.getElementById('agency');
    var agencyMask = new Inputmask('9999-9');
    agencyMask.mask(agencyInput);

    // Máscara para o campo Número da Conta
    var accNumInput = document.getElementById('accNum');
    var accNumMask = new Inputmask('999999-999999');
    accNumMask.mask(accNumInput);
</script>

</body>
</html>
