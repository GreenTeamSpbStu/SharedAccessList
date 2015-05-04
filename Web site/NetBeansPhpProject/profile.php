

<?php
include_once './common.php';
include_once './sessionCommon.php';
include_once './profileLogic.php';

$errorMsg = '';

$notifications = '';
$groups = '';
$profile = '';
$feeds = '';



$url = $apiDomain . '/user.get?token=' . $token . '&groups&profile';
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
        $profile = createProfile($json->profile->avatar, $json->profile->mail, $json->profile->name);
//        var_dump($json->groups);
//        var_dump($json->nofications);
    }
}


$groups = createGroup('Beer lovers', 'info', '-1', 13);

$notifications = createNotifications(array((object)
    array('data' => time(), 'type' => 'Invitation', 'content' => 'content')));

if (isset($_GET['id']))
{

    //отображаем страницу пользователя по id
} else
{
    //отображаем нашу страницу
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

<?php echo $_SESSION['token'], '<br>'; 
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

                <!--<div id="id-notifications-container" class="notifications-container"></div>-->

<?= $groups ?>

                <div class="left-column-container">
                    <h4>Join group</h4>
                </div>

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