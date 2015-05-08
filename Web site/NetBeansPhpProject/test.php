<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$url = 'https://fwallet.tk:25565/auth.api?mail=test@rambler.ru&passwd=123';
$response = http_get($url, array("timeout"=>2), $info);
var_dump($response);