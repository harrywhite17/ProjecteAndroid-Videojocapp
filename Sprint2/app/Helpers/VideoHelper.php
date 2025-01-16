<?php

namespace App\Helpers;

use App\Models\Video;

class VideoHelper
{
    public static function getDefaultVideos()
    {
        return Video::where('is_default', true)->take(5)->get();
    }
}