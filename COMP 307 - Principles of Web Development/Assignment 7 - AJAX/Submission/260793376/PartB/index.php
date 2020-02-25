<?php
require __DIR__ . '/vendor/autoload.php';

$app = new Slim\App;
$app->get('/', function ($request, $response) {
    return 'jello world';
});

$app->post('/main', function () {
    $data = $_POST['json'];
    $encodedData = base64_encode($data);
    passthru("python main.py $encodedData");
});

$app->run();
?>
