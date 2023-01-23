import React, { Component, useState } from "react";
import * as ReactDOM from "react-dom";
import { Navigate, Link } from "react-router-dom";
import AuthService from "../services/auth.service";
import { getMyData, sendEditedData } from "../services/instruktor.service";
import "../css/profile.css";

const ProfileView = (props) => {
  const [listaPredmeta, promijeniListuPr] = useState([]);
  const [komponenta, setKomp] = useState(0);
  //const [promjena, setPromjena] = useState(null);

  console.log(props.predajePredmete);
  console.log(props);
  if (
    props.predajePredmete !== undefined &&
    listaPredmeta.length === 0 &&
    komponenta === 0
  ) {
    setKomp(1);
    let tmpLista = [];
    for (var elem of props.predajePredmete) {
      tmpLista.push(elem.name);
    }
    promijeniListuPr(tmpLista);
  }

  console.log(listaPredmeta);

  const handleEdit = (e) => {
    e.preventDefault();
    window.location.href = "/edit-profile";
  };
  const handleIspit = (e) => {
    e.preventDefault();
    window.location.href = "/testovi";
  };

  return (
    <div>
      <link
        href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
        rel="stylesheet"
        id="bootstrap-css"
      ></link>

      <div className="container emp-profile">
        <form method="post">
          <div className="row">
            <div className="col-md-4">
              <div className="profile-img"></div>
            </div>
            <div className="col-md-6">
              <div className="profile-head">
                <h3>Pozdrav {props.firstName}!</h3>
                <h6>Instruktor</h6>

                <ul className="nav nav-tabs" id="myTab" role="tablist">
                  <li className="nav-item">
                    <a
                      className="nav-link active"
                      id="home-tab"
                      data-toggle="tab"
                      href="#home"
                      role="tab"
                      aria-controls="home"
                      aria-selected="true"
                    >
                      O meni
                    </a>
                  </li>
                </ul>
              </div>
            </div>
            <div className="col-md-2">
              <button
                type="submit"
                id="submit"
                name="submit"
                className="btn btn-primary"
                onClick={handleEdit}
              >
                Uredi profil
              </button>
            </div>
          </div>
          <div className="row">
            <div className="col-md-4">
              <div className="profile-work">
                <h4>Moji predmeti</h4>

                <div id="mojiPredmeti">
                  {listaPredmeta.length !== 0 ? (
                    listaPredmeta.map((elem) => {
                      return <p style={{ alignSelf: "center" }}>{elem}</p>;
                    })
                  ) : (
                    <p style={{ alignSelf: "safe center" }}>
                      Trenutno nije odabran niti jedan predmet
                    </p>
                  )}
                </div>

                <h4>Testovi</h4>
                <select id="predmet" name="selection">
                  <option value="MATEMATIKA">Matematika</option>
                  <option value="FIZIKA">Fizika</option>
                  <option value="HRVATSKI">Hrvatski</option>
                  <option value="ENGLESKI">Engleski</option>
                </select>

                <select id="razred" name="selection">
                  <option value="1ss">1.Srednje</option>
                  <option value="2ss">2.Srednje</option>
                  <option value="3ss">3.Srednje</option>
                  <option value="4ss">4.Srednje</option>
                </select>
                <button
                  type="submit"
                  id="ispit"
                  name="ispit"
                  className="btn btn-primary"
                  onClick={handleIspit}
                >
                  Odaberi ispit
                </button>
              </div>
            </div>
            <div className="col-md-8">
              <div className="tab-content profile-tab" id="myTabContent">
                <div
                  className="tab-pane fade show active"
                  id="home"
                  role="tabpanel"
                  aria-labelledby="home-tab"
                >
                  <div className="row">
                    <div className="col-md-6">
                      <label>Ime</label>
                    </div>
                    <div className="col-md-6">
                      <p>{props.firstName}</p>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col-md-6">
                      <label>Prezime</label>
                    </div>
                    <div className="col-md-6">
                      <p>{props.lastName}</p>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col-md-6">
                      <label>Email</label>
                    </div>
                    <div className="col-md-6">
                      <p>{props.email}</p>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col-md-6">
                      <label>Mobitel</label>
                    </div>
                    <div className="col-md-6">
                      <p>{props.mobileNum}</p>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col-md-6">
                      <label>Adresa</label>
                    </div>
                    <div className="col-md-6">
                      <p>{props.adresa}</p>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col-md-6">
                      <label>Opis</label>
                    </div>
                    <div className="col-md-6">
                      <p>{props.opis}</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      redirect: null,
      userReady: false,
    };
  }

  componentDidMount() {
    let currentUser = AuthService.getCurrentUser();
    this.setState();

    if (!currentUser) {
      this.setState({ redirect: "/home" });
    } else {
      getMyData().then(
        (response) => {
          this.setState({
            user: response.data,
            notFound: false,
          });
        },
        (error) => {
          this.setState({
            notFound: true,
          });
        }
      );

      this.setState({ currentUser: currentUser, userReady: true });
    }
  }

  render() {
    if (this.state.redirect) {
      return <Navigate to={this.state.redirect} />;
    } else if (this.state.notFound) {
      return <Navigate to={"/instruktori"} />;
    } else {
      return <ProfileView {...this.state.user} />;
    }
  }
}
