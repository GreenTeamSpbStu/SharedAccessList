<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$str = '{"groups":[{'
        . '"balance":0.0,'
        . '"id":1,'
        . '"creationDate":"2015-05-01 22:35:38.0",'
        . '"ownerId":17,'
        . '"description":"Группа быстрого реагирования для ослепления вражеской команды.",'
        . '"name":"Быдлокодеры"'
        . '}'
        . ']}';


//$str = '{"groups":[{"id":1, "balance":0.0, "creationDate": "2015-05-01 22:35:38.0"}]}';

$json = (object)json_decode($str);

foreach($json->groups as $g)
    var_dump($g);

