<?php

function view($name, $data = []): void
{
    extract($data);
    require "../app/View/$name.php";
}