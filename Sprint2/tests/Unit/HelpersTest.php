<?php

namespace Tests\Unit;

use App\Helpers\VideoHelper;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Support\Facades\Artisan;
use Tests\TestCase;
use App\Models\Video;

class HelpersTest extends TestCase
{
    use RefreshDatabase;

    protected function setUp(): void
    {
        parent::setUp();
        Artisan::call('migrate');
        $this->seedDefaultVideos();
    }

    public function testDefaultVideos()
    {
        $video = \App\Models\Video::create([
            'title' => 'Neque dolor aut inventore.',
            'url' => 'http://example.com/video1',
            'is_default' => true,
            'published_at' => '2023-04-13 23:23:48',
        ]);
        $this->assertTrue($video->is_default);
    }

    public function testDefaultVideosCreation()
    {
        $video = \App\Models\Video::create([
            'title' => 'Quo ad ex facere nemo accusantium ea et.',
            'url' => 'http://example.com/video2',
            'is_default' => true,
            'published_at' => '2018-01-23 13:55:47',
        ]);
        $this->assertTrue($video->is_default);
    }

    private function seedDefaultVideos()
    {
        Video::factory()->count(5)->create(['is_default' => true]);
    }

    public function test_default_videos_creation()
    {
        $defaultVideos = VideoHelper::getDefaultVideos();
        $this->assertCount(5, $defaultVideos);
    }
}