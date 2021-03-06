

<?php
include_once './common.php';
include_once './sessionCommon.php';
include_once './profileLogic.php';

$errorMsg = '';

$notifications = '';
$groups = '';
$profile = '';
$feeds = '';


$url = $apiDomain . '/user.get?';

if (isset($_GET['id']))
{
    $url.='id='.$_GET['id'];
}
else
{
    $url.='token=' . $token . '&groups&profile&invitations';
}


$response = http_get($url, array("timeout" => 2), $info);

if ($response == '')
{
    $errorMsg .= 'Api server not responding' . '<br>';
} else
{
    $parsed = http_parse_message($response);
    if ($parsed->{'responseCode'} != 200)
    {
        if (isset($parsed->{'body'}))
        {
            $errorMsg = json_decode($parsed->{'body'})->message;
        }
    } else
    {
        $json = (object)json_decode($parsed->{'body'});
        if (isset($json->profile))
            $profile = createProfile($json->profile->avatar, $json->profile->mail, $json->profile->name);
        
        if (isset($json->groups))
            $groups = createGroups($json->groups);
        
        if (isset($json->invitaitons))
            $notifications = createNotifications($json->invitaitons);
//        var_dump($json->groups);
//        var_dump($json->nofications);
    }
    
    if (isset($_GET['notificationId']))
    {
        $notificationUrl = $apiDomain.'/invitation.accept?token='.$token.'&id='.$_GET[notificationId];
        if (isset($_GET['notificationStatus']) and $_GET['notificationStatus'] == 'reject')
            $notificationUrl.='&reject';
        
        http_get($notificationUrl, array("timeout" => 2), $info);
        Redirect('profile.php');
    }
}

?>

<!DOCTYPE html>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" href="styles/common.css" media="screen" type="text/css"/>
        <link rel="stylesheet" href="styles/profile.css" media="screen" type="text/css"/>
        <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="js/common-scripts.js"></script>
        <script>
            $(function () {
                $('.block-hidden, .notifications-container').hide();
                $a = $('.block, .notifications');
                $a.on('click', function (event) {
                    event.preventDefault();
                    $a.not(this).next().slideUp(500);
                    $(this).next().slideToggle(500);
                });
            });
        </script>
    </head>
    <body>

<?php echo $_SESSION['token'], '<br>', $url, '<br>'; 
// var_dump($parsed->{'body'}); echo '<br><br>';
// var_dump($json); echo '<br><br>';
// var_dump($json->profile); echo '<br><br>';
 
// foreach ($json->groups as $item) {
//   var_dump($item);
//}
 
?>


        <div id="error-message"><?= $errorMsg ?></div>   

        <div class="page-container">

            <div class="left-column">

                <!--сворачивается элемент, следующий за '.notifications' и '.block'-->

                <?= $notifications ?>

                <?= $groups ?>
                
                <?php 
                    if (!isset($_GET['id']))
                    {
                        echo '<div class="left-column-container" id="join-group">'
                        .'<h4><span class="fontawesome-signin"></span>Join group</h4>'
                        .'</div>'
                        . '';
                    }
                    else
                    {
                        echo '<div class="left-column-container">'
                        .'<h4><span class="fontawesome-home"></span>Home</h4>'
                        .'</div>'
                        . '';
                    }
                ?>

            </div>

            <div class="middle-column">

            <?= $profile ?>

                <div class="middle-column-container">
                    <h3 style="padding-bottom: 2px"> Feed </h3>

            <?= $feeds ?>

<!--                    <p class="feeds-record">
                        "Геннадий Петрович" joined us!
                    </p>

                    <p class="feeds-record">
                        "Виктор Иванович" bought "Сибирская корона Классическое", "Жигулевское"!
                    </p>-->

                </div>

            </div>


        </div>


    </body>
</html>