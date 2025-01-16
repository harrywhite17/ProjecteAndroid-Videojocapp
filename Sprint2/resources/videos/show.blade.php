
<x-videos-app-layout>
    <div class="container">
        <h1>{{ $video->title }}</h1>
        <p>{{ $video->description }}</p>
        <p>Published: {{ $video->formatted_published_at }}</p>
        <video src="{{ $video->url }}" controls></video>
    </div>
</x-videos-app-layout>