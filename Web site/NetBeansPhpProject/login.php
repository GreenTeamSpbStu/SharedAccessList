<!DOCTYPE html>
<?php 
    include_once './common.php';
    
    $email = $_POST['email'];
    $password = $_POST['password'];
    $errorMsg = '';

    if ($email != '' and $password != '')
    {
        $auth = $apiDomain.'/auth.api?mail='.$email.'&passwd='.$password;
        $response = http_get($auth, array("timeout"=>2), $info);
        
        if ($response == '')
        {
            $errorMsg .= 'Api server not responding'.'<br>';
        }
        else 
        { 
            $parsed = http_parse_message($response);
            if ($parsed-> {'responseCode'} != 200)
            {
                if (isset($parsed->  {'body'}))
                {
                    $errorMsg = json_decode($parsed -> {'body'}) -> {'message'};
                }
            }
            else
            {
                $body = $parsed->  {'body'};
                $json = json_decode($body);
                $token = $json -> {'token'};
                print_r($token);

                session_destroy();

                session_start();
                $_SESSION['token'] = $token;
                
                $url = $domain.'/profile.php';
                echo $url, '<br>';
                Redirect($url);
            }
        }
    }
    
?>

<html>
<head lang="ru">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="styles/login.css" media="screen" type="text/css" />
    <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="js/common-scripts.js"></script>
</head>
<body>
    <div class="page-container">
        <div id="about">
            <h2>Friendly Wallet</h2>
            <h3>Collaborative shopping! Share your money!</h3>
        </div>

       <div id="login" class="form">
           <form action="login.php" method="post" >
                   <p><span class="fontawesome-user"></span><input name="email" type="text" placeholder="E-mail" required id="email"></p>
                   <p><span class="fontawesome-lock"></span><input name="password" type="password" placeholder="Password" required id="password"></p>
                   <p><input type="submit" value="LOG IN"></p>
           </form>

           <p>
                   <a href="signup.php">Sign up</a> or log in using
                   <a href="#"><img src="images/vk.png"></a>
                   <a href="#"><img src="images/google.png"></a>
           </p>
           <br>
           <div id="error-message"> <?=$errorMsg?> </div> 
       </div>
    </div>
</body>
</html>