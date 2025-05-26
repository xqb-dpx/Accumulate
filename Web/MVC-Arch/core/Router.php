<?php

namespace Core;
class Router
{
    protected static $routes = [];

    protected static function addRoutes($route, $controller, $action, $method)
    {
        self::$routes[$method][$route] = ["Controller" => $controller, "Action" => $action];
    }

    public static function getRoutes($route, $controller, $action)
    {
        self::addRoutes($route, $controller, $action, "GET");
    }

    public static function postRoute($route, $controller, $action)
    {
        self::addRoutes($route, $controller, $action, "POST");
    }

    public static function dispatch()
    {
        $uri = $_SERVER['REQUEST_URI'];
        $method = $_SERVER['REQUEST_METHOD'];
        foreach (self::$routes[$method] as $route) {
            $pattern = preg_match('/{\w+(:([^}]+))?}/', '([a-zA-Z0-9_-])', $route);
            var_dump($pattern);
            die();
        }
//        if (array_key_exists($uri, self::$routes[$method])) {
//            $controller = self::$routes[$method][$uri]["Controller"];
//            $action = self::$routes[$method][$uri]["Action"];
//            $controller = new $controller();
//            $controller->$action();
//        } else {
//            die("404 Not Found");
//        }
    }
}