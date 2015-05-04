<?php
include_once './common.php';

function createGroup($groupName, $groupInfo, $groupId, $balance)
{
    $res = '<div class = "block"><span class="fontawesome-group"></span>';
    $res .='<h4>'.$groupName.'</h4></div>'."\r\n";
    
    $res.= '<div class = "block-hidden">'.
            '<div class="left">'.$groupInfo.'</div>'.
            '<div class="right">Credits: '.$balance.'<br>'.
            '<button onclick=\'window.location.href="#"\' class="button" value="Group page"></button><br>'.
            '<button onclick=\'window.location.href="#"\' class="button" value="Order"></button><br></div>'.
            '</div>'."\r\n";
    return $res;
}