

<?php 
    include_once './common.php';
    include_once './sessionCommon.php';
    include_once './profileLogic.php';
    
    $notifications = '';
    $groups = '';
    
    $groups = createGroup('Beer lovers', 'info', '-1', 13);
    
    
    if (isset($_GET['id']))
    {
        
        //отображаем страницу пользователя по id
        
    }
    else
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

//        function requestAllData() {
//            $.ajax({
//                type: 'get',
//                url: domain + '/user.api?token=' + getCookie('token') + '&profile',
//                response: 'text',
//                async: false,
//                tryCount: 0,
//                retryLimit: 3,
//                timeout: 2000,
//                retryAfter: 5000,
//                success: function (res)
//                {
//                    updateAllInfo(JSON.parse(res));
//                },
//                error: function (res) {
//                    console.log(res);
//                    this.tryCount++;
//                    if (this.tryCount < this.retryLimit) $.ajax(this);
//                }
//            });
//
//            updateAllInfo(JSON.parse('{}')); // КОСТЫЛЬ!!! УБРАТЬ!!!!
//        }
//
//        function updateAllInfo(json)
//        {
//            if (json.profile != null) {
//                document.getElementById("id-person-profile").innerHTML = createProfile(json.profile);
//            }
//
//
//            if (json.notifications != null) {
//                var notifications_label = document.getElementById("id-notifications");
//                notifications_label.innerHTML = '<div class="notifications">' +
//                        '<span class="fontawesome-bullhorn"></span>' +
//                        '<h4> Notifications </h4></div>' +
//                        '<div class="notifications-container" id="id-notifications-container"></div>';
//
//                var notifications_container = document.getElementById("id-notifications-container");
//                notifications_container.innerHTML += createNotifications(json.notifications);
//            }
//
//            var groups = document.getElementById("id-groups");
//            groups.innerHTML = '<div class="left-column-container"><h4>Join Group</h4></div>'
//            if (json.groups != null) {
//                groups.innerHTML +=
//
//                        createGroups(json.groups);
//            }
//            groups.innerHTML += createGroup(JSON.parse('{"name":"Beer Lovers Beer Lovers Beeerererr",' +
//                    '"info":"Клуб любителей пива", "link":"#", "balance": "153"}'));
//
//        }
//
//        function createNotifications(json) {
//            var res = '';
//
//            for (var i = 0; i < json.length; i++) {
//                res += createNotification(json[i]);
//            }
//        }
//        function createNotification(json) {
//            return '<div class="notification"><p>' +
//                    json.data + ' ' + json.type + '</p>' + json.content + '</div>';
//        }
//
//        function createGroups(json) {
//            var res = '';
//
//            for (var i = 0; i < json.length; i++) {
//                res += createGroup(json[i]);
//            }
//        }
//        function createGroup(json) {
//            return '<div class = "block"><span class="fontawesome-group"></span>' +
//                    '<h4>' + json.name + '</h4></div>' +
//                    '<div class = "block-hidden">' + json.info + '<br>' +
//                    '<button onclick="window.location.href="' + json.link +'" class="button" value="Group page"></button><br>'+
//                    '<button onclick="window.location.href="' + '#' +'" class="button" value="Order"></button><br>' +
//                    'Credits: ' + json.balance + ' rub <br></div>';
//        }
//
//
//        function createProfile(json) {
//            return '<img src="' + json.avatar +
//                    '" class="photo80px" style="float: left" ' +
//                    'onerror="if (this.src != \'images/default_avatar.jpg\')' +
//                    'this.src = \'images/default_avatar.jpg\';"> <br>' +
//                    '<h4> E-mail: ' + json.mail + '</h4><br>' +
//                    '<h4>Name: ' + json.name + '</h4><br>' +
//                    'UserId: ' + getCookie('userId');
//        }

        $(function () {

//            requestAllData();

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

<div class="page-container">

    <div class="left-column">

        <!--сворачивается элемент, следующий за '.notifications' и '.block'-->

        <div><?=$notifications?></div>

        <!--<div id="id-notifications-container" class="notifications-container"></div>-->

        <?=$groups?>
            
<!--        <div class = "block"><span class="fontawesome-group"></span>
            <h4>Beeer lowersssssss</h4></div>

            <div class = "block-hidden">
                <div class="left">
                    If you only want to show a single line of
                    text in a fixed width div, give white-space:nowrap a go.
                    Together with overflow:hidden it will force your browser not
                    to break the line and crop the view to your forced width.
                </div>
                <div class="right">
                    <h4 style="color: green">101</h4>
                    <button onclick= 'window.location.href= "#"' class="button"> Group page </button> <br>
                    <button onclick= 'window.location.href= "#"' class="button"> Quick order </button> <br>

                </div>



            </div>-->
        
        <div class="left-column-container">
            <h4>Join group</h4>
        </div>

    </div>

    <div class="middle-column">

        <div id="profile" class="middle-column-container">
            <h3> Profile </h3>

            <div id="id-person-profile">
                <img src="" class="photo80px" style="float: left"> <br>
                <pre><h4 style="padding-bottom: 5px">E-mail:   skovoroda@skovoroda.ru</h4></pre>
                <pre><h4>Name:    skovoroda</h4></pre>
            </div>
            <br>
            <a href="edit_profile.html" style="float: right">Edit</a>
        </div>

        <div class="middle-column-container">
            <h3 style="padding-bottom: 2px"> Feed </h3>

            <p class="feeds-record">
            "Геннадий Петрович" joined us!
            </p>

            <p class="feeds-record">
            "Виктор Иванович" bought "Сибирская корона Классическое", "Жигулевское"!
            </p>
        </div>

    </div>


</div>


</body>
</html>