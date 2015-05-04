<!DOCTYPE html>
<?php
    include_once './common.php';
    
    $errorMsg = '';
    $email = '';
    $name = '';
    
    if (!empty($_POST))
    {
        $email = isset($_POST['email'])? $_POST['email'] : '';
        $name = isset($_POST['name'])? $_POST['name'] : '';
        $password = isset($_POST['password'])? $_POST['password'] : '';
        $password2 = isset($_POST['password2'])? $_POST['password2'] : '';
        
        $valid = true;
        if ($email=='' or !preg_match("/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i", $email))
        {
            $errorMsg = 'Invalid email format'.'<br>';
            $valid = false;
        }

        if ($name=='' or !preg_match("/[А-Яа-яA-Za-z][А-Яа-яA-Za-z _]+/i", $name))
        {
            $errorMsg .= 'Invalid name format'.'<br>';
            $valid = false;
        }

        if (strlen($password) < 4)
        {
            $errorMsg .= 'Password is too short'.'<br>';
            $valid = false;
        }

        if ($password != $password2)
        {
            $errorMsg .= 'Passwords doesn`t match'.'<br>';
            $valid = false;
        }
        
        if ($valid)
        {
            $signupUrl = $apiDomain.'/reg?mail='.$email.'&name='.urlencode($name).'&passwd='.$password; // 'http://94.188.80.116:25565/reg?mail=test6@rambler.ru&name=Dmitrii&passwd=12345';//
            echo $signupUrl, '<br>';
            $response = http_get($signupUrl, array("timeout"=>4), $info);
            if ($response == '')
            {
                $errorMsg .= 'Api server not responding'.'<br>';
            }
            else
            {
                $parsed = http_parse_message($response);
                if ($parsed-> {'responseCode'} != 200)
                {
                    if (isset($parsed->  {'body'}) )
                    {
                        if (isset($parsed->  {'body'}))
                        {
                            $json = json_decode($parsed -> {'body'});
                            if (isset($json -> {'message'}))
                                $errorMsg .= $json -> {'message'};
                            else
                                $errorMsg .= $parsed -> {'body'};
                        }
                        else
                            $errorMsg .= $parsed;
                    }
                }
                else
                {
                    Redirect($domain.'/login.php');
                }
            }
        }
    }

?>


<html>
<head lang="ru">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="styles/signup.css" media="screen" type="text/css"/>
    <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
</head>
<body>

<div class="page-container">
    <div class="form">
        <h4>Sign up</h4>
        <hr>
        <form action="signup.php" method="post" accept-charset="UTF-8">
            <p> E-mail: <input type="text" required id="email" name="email" value= "<?= $email ?>"></p>
            <p> Password: <input type="password" required id="password1" name="password"></p>
            <p> Repeat password: <input type="password" required id="password2" name="password2"></p>
            <p> Name: <input type="text" required id="name" name="name" value="<?= $name ?>"></p>
            <p><input type="submit" value="SIGN UP"></p>

        </form>
        <div id="error-message"> <?=$errorMsg?> </div> 
    </div>
</div>

</body>
</html>