'use strict';

class ProductRow extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.contact.name}</td>
                <td>{this.props.contact.address}</td>
                <td>{this.props.contact.phone}</td>
            </tr>
        );
    }
}

class ProductTable extends React.Component {
    render() {

        let rows = this.props.contacts.map( (contact) => {
            if (contact.name.indexOf(this.props.filterQuery) === -1) {
                return;
            }
            return (
                <ProductRow key={contact.id} contact={contact} />
            );
        });

        return (
            <div className="row">
                <div className="col-md-10">
                    <table className="table table-bordered ">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Phone</th>
                        </tr>
                        </thead>
                        <tbody>
                        {rows}
                        </tbody>
                    </table>
                </div>
            </div>
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
            <form className="form-inline">
                <div className="form-group">
                    <label className="sr-only" htmlFor="search">Email address</label>
                    <input type="text" className="form-control" id="search" placeholder="Search by name ..." onChange={this.handleChange}/>
                </div>
            </form>
        );
    }
}

class ContactForm extends React.Component {

    constructor(props) {
        super(props);

        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeAddress = this.onChangeAddress.bind(this);
        this.onChangePhone = this.onChangePhone.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

        this.state = {
            name : '',
            address : '',
            phone : ''
        }
    }

    onChangeName(e) {
        this.setState({name : e.target.value});
    }

    onChangeAddress(e) {
        this.setState({address : e.target.value});
    }

    onChangePhone(e) {
        this.setState({phone : e.target.value});
    }

    onSubmit(e) {
        e.preventDefault();
        let contact = {name : this.state.name, address : this.state.address, phone : this.state.phone};
        this.props.onFormSubmit(contact, ()=> {
            document.getElementById('contactForm').reset();
            this.setState({name : '', address : '', phone : ''});
        });
    }

    render() {
        return (
            <div>
                <form id='contactForm' onSubmit={this.onSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Contact name</label>
                        <input onChange={this.onChangeName} className="form-control" id="name" type='text' placeholder='Petya Ivanov' />
                    </div>
                    <div className="form-group">
                        <label htmlFor="address">Address</label>
                        <input onChange={this.onChangeAddress} className="form-control" id="address" type='text' placeholder='ex. Kazan, 420055' />
                    </div>
                    <div className="form-group">
                        <label htmlFor="phone">Phone number</label>
                        <input onChange={this.onChangePhone} className="form-control" id="phone" type='text' placeholder='ex. 8-800-2000-600' />
                    </div>

                    <button disabled={this.props.isDisabled} id="add-btn" className="btn btn-success" type="submit">Add</button>
                </form>
            </div>
        );
    }
}

class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            contacts : [],
            filterQuery : '',
            isFetching : false
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

        //disable button
        this.setState({isFetching : true});


        fetch(url, {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body : JSON.stringify(contact)
        })
            .then( (response) => {
                return response.json();
            })
            .then( (data) => {
                this.setState({isFetching : false});
                let newContacts = this.state.contacts;
                newContacts.push(data);
                this.setState({contacts : newContacts});
                callback(); //reset form input
            })
            .catch( (err) => {
                this.setState({isFetching : false});
                alert('Error occurred!');
                console.error(err);

            });

    }

    componentDidMount() {

        let jsonContacts = [];

        fetch(url)
            .then( (response) => {
                return response.json();
            })
            .then( (json) => {
                jsonContacts = json._embedded.contacts;
                this.setState({contacts : jsonContacts, filterQuery : ''});
            })
            .catch( (err) => {
                console.error(err);
                alert('Some error occurred!')
            });
    }

    render() {
        return (
            <div className="jumbotron">
                <SearchBar onUserInput={this.handleUserInput} filterQuery={this.state.filterQuery} />

                <br/>

                {this.state.contacts.length === 0 ? <h3>There is no data yet</h3> :
                        <ProductTable contacts={this.state.contacts} filterQuery={this.state.filterQuery}/>}

                <ContactForm isDisabled={this.state.isFetching} onFormSubmit={this.handleFormSubmit} />
            </div>
        );
    }
}

const url = 'http://localhost:8080/contacts';



ReactDOM.render(<App />, document.getElementById('content'));