<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>{{ config('app.name', 'Laravel') }}</title>
    <!-- Add your styles and scripts here -->
</head>
<body>
<div id="app">
    <!-- Add your header, navigation, etc. here -->
    <main>
        {{ $slot }}
    </main>
    <!-- Add your footer here -->
</div>
</body>
</html>