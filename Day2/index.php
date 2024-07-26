<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Greeting Page</title>
    <style>
        body {
            background-color: purple;
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
        }
        h1 {
            font-size: 3em;
        }
    </style>
</head>
<body>
    <?php
        // Greeting message
        $greeting = "Hello, welcome to my website!=))";
        echo "<h1>$greeting</h1>";
    ?>
</body>
</html>
