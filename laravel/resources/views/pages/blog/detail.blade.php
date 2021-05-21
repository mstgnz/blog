@extends('layout')

@section('content')

<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <div class="post-preview">
                <a href="{{ $blog->slug }}">
                    <h2 class="post-title">{{ $blog->title }}</h2>
                </a>
                <p>{!! $blog->short_text !!}</p>
                <p>
                    {!! $blog->long_text !!}
                </p>
                <p class="post-meta">
                    <a href="/user/profile/{{ $blog->user->id }}">{{ $blog->user->firstname }} {{ $blog->user->lastname }}</a> created at {{ $blog->create_date }}
                </p>
            </div>
        </div>
    </div>
</div>
    
@endsection