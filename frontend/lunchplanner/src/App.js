// App.js
import React from "react";
import logo from "./logo.svg";
import "./App.css";
import axios from "axios";

import Contact from "./components/Contact";

class App extends React.Component {

    // default State object
    state = {
        contacts: []
    };

    componentDidMount() {
        axios
            .get("http://localhost:8080/home")
            .then(response => {
                console.log(response.data);

                // create an array of contacts only with relevant data
                const newContacts = response.data;

                // create a new "State" object without mutating
                // the original State object.
                const newState = Object.assign({}, this.state, {
                    contacts: newContacts
                });

                // store the new state object in the component's state
                this.setState(newState);
            })
            .catch(error => console.log(error));
    }

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo" />
                    <h1 className="App-title">Welcome to our Contact Manager</h1>
                </header>

                <Contact name={this.state.contacts} />
            </div>
        );
    }
}

export default App;