<?php
ini_set("log_errors", 1);
ini_set("error_log", "php-error.log");

$domain = 'https://fwallet.tk';//'https://fwallet.tk:25565';
$apiDomain = 'https://fwallet.tk:25565';//'http://94.188.80.116:25565';


function Redirect($url, $permanent = false)
{
    if (headers_sent() === false)
    {
    	header('Location: ' . $url, true, ($permanent === true) ? 301 : 302);
    }
    exit();
}

