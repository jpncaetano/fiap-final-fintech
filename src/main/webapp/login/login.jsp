<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous"
    />
    <link rel="stylesheet" href="styles/index.css" />
    <title>Money Matters</title>
</head>
<body>
<div class="container">
    <img src="https://i.imgur.com/vXWhCCU.png" alt="logo-fundo-branco"/>

    <!-- Formulário de Login -->
    <form action="login" method="post">

        <!-- Campo de entrada para o Email -->
        <div class="form-outline mb-4">
            <input
                    name="email"
                    placeholder="Email"
                    type="email"
                    id="form2Example1"
                    class="form-control"
                    required
            />
        </div>

        <!-- Campo de entrada para a Senha -->
        <div class="form-outline mb-4">
            <input
                    name="password"
                    placeholder="Senha"
                    type="password"
                    id="form2Example2"
                    class="form-control"
                    required
            />
        </div>

        <!-- Botão de Login -->
        <div class="text-center">
            <button
                    type="submit"
                    class="btn btn-primary btn-block mb-3"
            >
                LOGIN
            </button>
        </div>

        <!-- Link de registro -->
        <div class="text-center second-container">
            <p class="mt-sm"><a href="signup">REGISTRE-SE</a></p>
        </div>

    </form>

    <!-- Exibir mensagem de erro, caso exista -->
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
    <div class="alert alert-danger mt-4" role="alert">
        <%= errorMessage %>
    </div>
    <%
        }
    %>

</div>
</body>
</html>
