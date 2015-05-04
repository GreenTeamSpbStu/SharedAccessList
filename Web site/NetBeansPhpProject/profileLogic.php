<?php
include_once './common.php';

function createGroup($groupName, $groupInfo, $groupId, $balance)
{
    $res = '<div class = "block"><span class="fontawesome-group"></span>
            <h4>'.$groupName.'</h4></div>

            <div class = "block-hidden">
                <div class="left">'.$groupInfo.'</div>
                <div class="right">
                    <h4 style="color: green">'.$balance.'</h4>
                    <button onclick= \'window.location.href= "#"\' class="button"> Group page </button> <br>
                    <button onclick= \'window.location.href= "#"\' class="button"> Quick order </button> <br>
                </div>
            </div>';
    return $res;
}

function createNotifications($arr)
{
    if (empty($arr))
        return '';
    
    
    $res = '<div class="notifications"> <span class="fontawesome-bullhorn"></span>
            <h4> Notifications </h4></div>
            <div class="notifications-container">
            ';
    
    foreach ($arr as $elem)
    {
        $res.='<div class="notification"><p>
        '.$elem->data.' '.$elem->type.'</p>'.$elem->content.'</div>'
         .'';
    }
    
    $res.='</div>';
    return  $res;
}

function createProfile($avatar, $mail, $name)
{
    $res = '<div id="profile" class="middle-column-container">
            <h3> Profile </h3>

            <div id="id-person-profile">
                <img src="'.$avatar.'" class="photo80px" style="float: left"> <br>
                <pre><h4 style="padding-bottom: 5px">E-mail:   '.$mail.'</h4></pre>
                <pre><h4>Name:    '.$name.'</h4></pre>
            </div>
            <br>
            <a href="edit_profile.html" style="float: right">Edit</a>
        </div>';
                
        //$json -> {'avatar'}
        //$json ->{'mail'}
                
    return $res;
}

function createFeedRecord($date, $content)
{
    $res = '<p class="feeds-record">'.$content.'</p>';
    return $res;
}