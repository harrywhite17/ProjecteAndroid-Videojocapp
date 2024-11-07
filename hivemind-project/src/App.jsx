import NavigationBar from "./components/navbar.jsx";
import HeroSection from "./components/herosection.jsx";
import FeaturesSection from "./components/features.jsx";
import Testimonis from "./components/testimonis";
import TeamsSection from "./components/teams.jsx";
import './index.css';

function App() {
    return (
        <div>
            <NavigationBar />
            <HeroSection />
            <Testimonis />
            <TeamsSection />
            <FeaturesSection />
        </div>
    );
}

export default App;



