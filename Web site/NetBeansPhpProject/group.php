<!DOCTYPE html>

<?php 

include_once './common.php';
include_once './sessionCommon.php';

function createGroupHome($group)
{
    $res = '<div class="middle-column-container" >
                <h3>'.$group->name.'</h3>
                <img src="" class="photo80px" style="float: left">
				<p>Контакт:</p>......<br>
				<p>Дата создания:</p>'.$group->creationDate.'
                <p>Описание:</p> &nbsp; '.$group->description.' <br>
            </div>
            ';
    return $res;
}


$errorMsg = '';
$home = '';


if (isset($_GET['id']))
{
    $url = $apiDomain . '/group.get?id=' . $_GET['id'];
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
            $home = createGroupHome($json);
        }
    }
}
 else
{
      $errorMsg .= 'Group id not specified' . '<br>';
}

?>



<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="styles/common.css" media="screen" type="text/css" />
    <link rel="stylesheet" href="styles/group.css" media="screen" type="text/css" />
    <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>

    <script>
        $(function(){
            SwitchToBlock($('#home'));
        });

        function SwitchToBlock(id)
        {
            $('.middle-column').not(id).hide();
            id.show(400);
        };


    </script>

    <!--<script>-->
    <!--$(function()-->
    <!--{-->
    <!--$('.block-hidden, .notifications-container').hide();-->
    <!--$a = $('.block, .notifications');-->
    <!--$a.on('click', function(event)-->
    <!--{-->
    <!--event.preventDefault();-->
    <!--$a.not(this).next().slideUp(500);-->
    <!--$(this).next().slideToggle(500);-->
    <!--});-->
    <!--});-->
    <!--</script>-->


</head>
<body>
    <div id="error-message"><?= $errorMsg ?></div>
    
    <div class="page-container">
        
        <div class="left-column">

            <div class="block" onclick="SwitchToBlock($('#home'))">
                <h4>Home</h4>
            </div>

            <div class="block" onclick="SwitchToBlock($('#orders'))">
                <h4>Orders</h4>
            </div>

            <div class="block" onclick="SwitchToBlock($('#members'))">
                <h4>Members</h4>
            </div>

            <div class="block" onclick="SwitchToBlock($('#history'))">
                <h4>Operations history</h4>
            </div>

            <br>

            <div class="block">
                <h4>Leave this group</h4>
            </div>
			
        </div>

        <div class="middle-column" id="home">
            
            <?=$home?>
            
<!--            <div class="middle-column-container" >
                <h3> Beer lovers </h3>
                <img src="images/beer.png" class="photo80px" style="float: left">
				<p>Контакт:</p>beerLovers@gmail.com<br>
				<p>Дата создания:</p>3 января 2012
                <p>Описание:</p> &nbsp; Партия любителей пива — политическая партия альтернативистского типа в Российской Федерации,
				зарегистрированная Минюстом РФ 9 августа 1994 года.	Партия была создана 13 декабря 1993 года в ходе совместного употребления пива по случаю их поражения на выборах. <br>
            </div>-->
            
            
            <br>
            
            
            <div class="middle-column-container">
                <h3> Feed </h3>
<!--                <div class="feeds-record">
                    "Геннадий Петрович" joined us!
                </div>

                <div class="feeds-record">
                    "Виктор Иванович" bought "Сибирская корона Классическое", "Жигулёвское"!
                </div>-->

            </div>
        </div>

        <div class="middle-column" id="orders">
            <div class="middle-column-container">
                <p>Orders</p>
            </div>
        </div>

        <div class="middle-column" id="members">
            <div class="middle-column-container">
                <p>Members</p>
            </div>
        </div>

        <div class="middle-column" id="history">
            <div class="middle-column-container">
                <p>History</p>
            </div>
        </div>


    </div>



</body>
</html>