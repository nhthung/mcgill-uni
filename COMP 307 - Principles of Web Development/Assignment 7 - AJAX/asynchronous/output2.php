<?php
/**
 * Created by PhpStorm.
 * User: faizy
 * Date: 2018-11-06
 * Time: 1:25 PM
 */

if ($_SERVER['REQUEST_METHOD'] === 'POST') {

//    print_r($_POST);

    echo "This is Form2 output and your Roll Number is " . $_POST['roll'];

}

?>