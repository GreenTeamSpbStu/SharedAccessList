<!DOCTYPE html>
<?php include_once './common.php';?>

<html>
<head lang="ru">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="styles/login.css" media="screen" type="text/css" />
    <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="js/common-scripts.js"></script>
</head>
<body>
    
    <?php 
    
    if (!extension_loaded('php_http'))
    {
        echo "php_http: MISSING", '<br>';
        return;
    }
    
    $url = 'https://fwallet.tk:25565/auth.api?mail=suhininalex@gmail.com&passwd=11235813';
    $response = http_get($url, array("timeout"=>1), $info);
    print_r($response);
//    for ($index = 0; $index < 4; $index++) {
//        
//    echo $_GET['email'], '<br>', $_GET['password'];
//    }
    
    
    ?>


    
    <div class="page-container">
        <div id="about">
            <h2>Friendly Wallet</h2>
            <h3>Collaborative shopping! Share your money!</h3>
        </div>

       <div id="login" class="form">
           <form action="login.php" method="get">
                   <p><span class="fontawesome-user"></span><input name="email" type="text" placeholder="E-mail" required id="email"></p>
                   <p><span class="fontawesome-lock"></span><input name="password" type="password" placeholder="Password" required id="password"></p>
                   <p><input type="submit" value="LOG IN"></p>
           </form>

           <p>
                   <a href="signup.html">Sign up</a> or log in using
                   <a href="#"><img src="images/vk.png"></a>
                   <a href="#"><img src="images/google.png"></a>
           </p>
            <br>
           <div id="error-message"></div>
       </div>
    </div>
</body>
</html>