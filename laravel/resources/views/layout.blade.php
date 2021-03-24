<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>{{ $title ?? 'blog' }}</title>
    <meta name="description" content="{{ $description ?? 'blog' }}" />
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <!-- JQuery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" />
    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <!-- CSS -->
    <link rel="stylesheet" href="{{ asset('css/clean-blog.min.css')}}" />
    <link rel="stylesheet" href="{{ asset('css/custom.css')}}" />
    @yield('css')
</head>
<body>

    <!-- Header -->
    @include('header')
    
    <!-- Content -->
    @yield('content')

    <!-- Footer -->
    @include('footer')
    
    <!-- JS Files -->
    <script src="{{ asset('js/custom.js')}}"></script>
    @yield('js')
</body>
</html>