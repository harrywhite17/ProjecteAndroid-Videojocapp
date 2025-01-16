<?php

namespace App\Http\Controllers;

use App\Models\Video;
use Illuminate\Http\Request;

class VideosController extends Controller
{
    public function index()
    {
        $videos = Video::all();

        /** @var view-string $view */
        $view = 'videos.index';
        return view($view, ['videos' => $videos]);
    }

    public function show(Video $video)
    {
        /** @var view-string $view */
        $view = 'videos.show';
        return view($view, compact('video'));
    }

    public function store(Request $request)
    {
        $data = $request->validate([
            'title' => 'required|string',
            'url' => 'required|string', // Ensure URL is validated before storing
            'published_at' => 'nullable|date',
            'is_default' => 'nullable|boolean',
        ]);

        $video = Video::create($data);

        return redirect()->route('videos.show', $video);
    }

    public function update(Request $request, Video $video)
    {
        $data = $request->validate([
            'title' => 'required|string',
            'url' => 'required|string', // Ensure URL is validated before updating
            'published_at' => 'nullable|date',
            'is_default' => 'nullable|boolean',
        ]);

        $video->update($data);

        return redirect()->route('videos.show', $video);
    }
}
