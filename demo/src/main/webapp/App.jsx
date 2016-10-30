'use strict';

import React from 'react';

class ProductRow extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.contact.fullName}</td>
                <td>{this.props.contact.city}</td>
                <td>{this.props.contact.phone}</td>
            </tr>
        );
    }
}

class ProductTable extends React.Component {
    render() {

        let rows = this.props.contacts.map( (contact) => {
            if (contact.fullName.indexOf(this.props.filterQuery) === -1) {
                return;
            }
            return (
                <ProductRow key={contact.id} contact={contact} />
            );
        });

        return (
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>City</th>
                    <th>Phone</th>
                </tr>
                </thead>
                <tbody>
                {rows}
                </tbody>
            </table>
        );
    }
}

class SearchBar extends React.Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(e) {
        this.props.onUserInput(e.target.value);
    }

    render() {
        return (
            <form>
                <input type="text" placeholder="Search..." onChange={this.handleChange} />
            </form>
        );
    }
}

class ContactForm extends React.Component {

    constructor(props) {
        super(props);

        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeCity = this.onChangeCity.bind(this);
        this.onChangePhone = this.onChangePhone.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

        this.state = {
            name : '',
            city : '',
            phone : ''
        }
    }

    onChangeName(e) {
        this.setState({name : e.target.value});
    }

    onChangeCity(e) {
        this.setState({city : e.target.value});
    }

    onChangePhone(e) {
        this.setState({phone : e.target.value});
    }

    onSubmit(e) {
        e.preventDefault();
        let contact = {fullName : this.state.name, city : this.state.city, phone : this.state.phone};
        this.props.onFormSubmit(contact, ()=> {
            document.getElementById('contactForm').reset();
            this.setState({name : '', city : '', phone : ''});
        });
    }

    render() {
        return (
            <div>
                <form id='contactForm' onSubmit={this.onSubmit}>
                    <input onChange={this.onChangeName} type='text' placeholder='Name...' />
                    <br/>
                    <input onChange={this.onChangePhone} type='text' placeholder='Phone...' />
                    <br/>
                    <input onChange={this.onChangeCity} type='text' placeholder='City...' />
                    <br/>
                    <button type='submit'>Add</button>
                </form>
            </div>
        );
    }
}

class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            contacts: [],
            filterQuery: ''
        };

        this.handleUserInput = this.handleUserInput.bind(this);
        this.handleFormSubmit = this.handleFormSubmit.bind(this);
    }


    handleUserInput(filterQuery) {
        this.setState({
            filterQuery : filterQuery
        });
    }

    handleFormSubmit(contact, callback) {
        //TODO API request to save new contact
        let newContacts = this.state.contacts;
        contact.id = this.state.contacts.length + 1;
        newContacts.push(contact);
        this.setState({contacts : newContacts});
        callback(); //reset form input
    }

    componentDidMount() {
        //TODO API request
        fetch(url, {mode : 'no-cors'})
            .then( (contacts) => {
                console.log(contacts.status);
            })
            .catch( (err) => {
                console.error(err);
            });

        this.setState({contacts : [], filterQuery : ''});
    }

    render() {
        return (
            <div>
                <SearchBar onUserInput={this.handleUserInput} filterQuery={this.state.filterQuery} />
                <ProductTable contacts={this.state.contacts} filterQuery={this.state.filterQuery}/>
                <ContactForm onFormSubmit={this.handleFormSubmit} />
            </div>
        );
    }
}

const url = 'http://localhost:8080/contacts';

export default App;
