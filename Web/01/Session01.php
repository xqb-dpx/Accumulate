<!DOCTYPEHTML>
<html lang="en" dir="ltr">
<head>
    <title>Sample</title>
    <link rel="stylesheet" href="style/index.css" type="text/css">
    <link rel="stylesheet" href="script/index.js">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<div class="section">
    <?php
    $TEST = "Please ";
    $arr01 = Array("01", "02", "03", "04", "05", "06", "07", "08", "09");
    $arr02 = [];

    for($i = 0; $i <= 30; $i++) {
        $arr02[] = "Day " . $i;
    }
    echo (var_dump($TEST) . '<input id="Loc" type="button" value="Press" onclick="Loc()">');
    ?>
</div>
<script src="script/index.js">

</script>
</body>
</html>