@foreach ($blogs as $item)
    <div class="post-preview">
        <a href="{{ $item->slug }}">
            <h2 class="post-title">{{ $item->title }}</h2>
        </a>
        <p>{!! $item->short_text !!}</p>
        <p class="post-meta">
            <a href="/user/profile/{{ $item->user->id }}">{{ $item->user->firstname }} {{ $item->user->lastname }}</a> created at {{ $item->create_date }}
        </p>
    </div>
    <hr>
@endforeach