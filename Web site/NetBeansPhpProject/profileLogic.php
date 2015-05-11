<?php
include_once './common.php';
include_once './sessionCommon.php';

function createGroups($arr)
{
    if (empty($arr))
        return '';
    
    $res = '';
    foreach ($arr as $elem)
    {
        $res.= createGroup($elem->group->name, $elem->group->description, $elem->group->id, $elem->balance);
    }
    return $res;
}

function createGroup($groupName, $groupInfo, $groupId, $balance)
{
    $res = '<div class = "block"><span class="fontawesome-group"></span>
            <h4>'.$groupName.'</h4></div>

            <div class = "block-hidden">
                <div class="left">'.$groupInfo.'</div>
                <div class="right">
                    <h4 style="color: green">'.$balance.'</h4>
                    <button onclick= \'window.location.href= "group.php?id='.$groupId.'"\' class="button"> Group page </button> <br>
                    <button onclick= \'window.location.href= "#"\' class="button"> Quick order </button> <br>
                </div>
            </div>
            ';
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
        $link = 'profile.php?notificationId='.$elem->invitationId;
        
        $res.='<div class="notification"><p>
        '.$elem->invitationTime.' '.'Invitation'.'</p>'
        . ''
        .'<p>You\'ve invited to group <a href="group.php?id='.$elem->group->id.'">'.$elem->group->name.'</a></p>'
        .'<p>by <a href="profile="profile.php?id='.'-1'.'">'.$elem->sender->name.'</a></p>'
        .'<button class="button" onclick="window.location.href=\''.$link.'\'">Accept</button> '
        .'<button class="button" onclick="window.location.href=\''.$link.'&notificationStatus=reject'.'\'">Deny</button>'
        .'<br>'.$link
        .'</div>';
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