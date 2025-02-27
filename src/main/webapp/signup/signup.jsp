<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
  <link rel="stylesheet" href="styles/index.css" />
  <title>Money Matters - Signup</title>
  <!-- Adicionando a biblioteca Inputmask -->
  <script src="https://cdn.jsdelivr.net/npm/inputmask@5.0.7/dist/inputmask.min.js"></script>
</head>

<body>
<div class="container">
  <img src="https://i.imgur.com/vXWhCCU.png" alt="logo-fundo-branco"/>

  <form method="POST" action="signup">
    <!-- Display error message if exists -->
    <c:if test="${not empty errorMessage}">
      <div class="alert alert-danger" role="alert">
          ${errorMessage}
      </div>
    </c:if>

    <div data-mdb-input-init class="form-outline mb-2">
      <input name="firstName" placeholder="Nome" type="text" class="form-control" required />
    </div>
    <div data-mdb-input-init class="form-outline mb-2">
      <input name="lastName" placeholder="Sobrenome" type="text" class="form-control" required />
    </div>
    <div data-mdb-input-init class="form-outline mb-2">
      <input name="email" placeholder="Email" type="email" class="form-control" required />
    </div>
    <div data-mdb-input-init class="form-outline mb-2">
      <input name="password" placeholder="Senha" type="password" class="form-control" required />
    </div>
    <div data-mdb-input-init class="form-outline mb-2">
      <input id="phone" name="phone" placeholder="Telefone" type="text" class="form-control" />
    </div>

    <div class="text-center">
      <button style="width: 150px" type="submit" class="btn btn-primary btn-block mb-4">
        CADASTRAR
      </button>
    </div>
    <div class="text-center second-container">
      <p class="mt-sm"><a href="login">Já é cadastrado?</a></p>
    </div>
  </form>
</div>

<script>
  var phoneInput = document.getElementById('phone');
  var im = new Inputmask('+99 99 99999-9999');
  im.mask(phoneInput);
</script>
</body>
</html>
