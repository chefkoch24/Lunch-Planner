import React from "react";
import PropTypes from "prop-types";

function Contact(props) {
    return (
        <div className="contact">
            <span>{props.name}</span>
        </div>
    );
}

// Typüberprüfung
//Contact.propTypes = {
//    name: PropTypes.string.isRequired
//};

export default Contact;