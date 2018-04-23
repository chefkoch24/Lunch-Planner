// App.js
import React from "react";
import logo from "./logo.svg";
import "./App.css";

import Contact from "./components/Contact";

class App extends React.Component {
    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo" />
                    <h1 className="App-title">Welcome to our Contact Manager</h1>
                </header>

                <Contact name="Leanne Graham" />
            </div>
        );
    }
}

export default App;