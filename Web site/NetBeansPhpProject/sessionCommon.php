<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$lifetime=600;

session_start();
setcookie(session_name(),session_id(),time()+$lifetime);

//if (!isset($_SESSION['token']))
//    Redirect($domain.'/login.php');