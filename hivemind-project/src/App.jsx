import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import NavigationBar from "./components/navbar.jsx";
import HeroSection from "./components/herosection.jsx";
import FeaturesSection from "./components/features.jsx";
import NewsLetter from "./components/newsletter.jsx";
import Testimonis from "./components/testimonis";
import TeamsSection from "./components/teams.jsx";
import ProductsContent from './components/products.jsx';   
import './index.css';

function App() {
    return (
        <Router>
            <div>
                <NavigationBar />
                <Routes>
                    <Route path="/" element={
                        <>
                            <HeroSection />
                            <Testimonis />
                            <TeamsSection />
                            <FeaturesSection />
                            <NewsLetter />
                        </>
                    } />
                    <Route path="/products" element={
                        <ProductsContent />
                    } />
                </Routes>
            </div>
        </Router>
    );
}

export default App;



