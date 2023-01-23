import { getMyData, sendEditedData } from "../services/instruktor.service";
import React, { Component, useState, useCallback } from "react";
import { Navigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import { loadFull } from "tsparticles";
import Particles from "react-tsparticles";

const EditProfileView = (props) => {
  const init = useCallback(async (engine) => {
    await loadFull(engine);
  });

  console.log(props);
  console.log(props.firstName);

  const [upisanoIme, setUpisanoIme] = useState("");
  const [upisaniEmail, setEmail] = useState("");
  const [upisaniBroj, setBroj] = useState("");
  const [upisanoPrezime, setPrezime] = useState("");
  const [upisanaAdresa, setAdresa] = useState("");
  const [upisanOpis, setOpis] = useState("");
  const [listaPredmeta, setListuPredmeta] = useState([]);
  const [komponenta, setKomponenta] = useState(0);
  const [predmetiCheckbox, setPredmeti] = useState({
    matematika: false,
    hrvatski: false,
    fizika: false,
    engleski: false,
  });

  if (
    props.firstName !== undefined &&
    listaPredmeta.length === 0 &&
    komponenta === 0
  ) {
    console.log(props);
    setBroj(props.mobileNum);
    setUpisanoIme(props.firstName);
    setPrezime(props.lastName);
    setAdresa(props.adresa);
    setOpis(props.opis);
    setKomponenta(1);
    console.log("uslo");
    let tmpLista = [];
    for (var elem of props.predajePredmete) {
      tmpLista.push(elem.name);
      if (elem.name === "MATEMATIKA")
        setPredmeti((prevState) => {
          return { ...prevState, matematika: true };
        });
      if (elem.name === "FIZIKA")
        setPredmeti((prevState) => {
          return { ...prevState, fizika: true };
        });
      if (elem.name === "ENGLESKI")
        setPredmeti((prevState) => {
          return { ...prevState, engleski: true };
        });
      if (elem.name === "HRVATSKI")
        setPredmeti((prevState) => {
          return { ...prevState, hrvatski: true };
        });
    }
    setListuPredmeta(tmpLista);
    console.log(tmpLista);
  }

  const fizikaHandler = (event) => {
    setPredmeti((prevPredmeti) => {
      return { ...prevPredmeti, fizika: prevPredmeti.fizika ? false : true };
    });
  };

  const hrvatskiHandler = (event) => {
    setPredmeti((prevPredmeti) => {
      return {
        ...prevPredmeti,
        hrvatski: prevPredmeti.hrvatski ? false : true,
      };
    });
  };

  const matematikaHandler = (event) => {
    setPredmeti((prevPredmeti) => {
      return {
        ...prevPredmeti,
        matematika: prevPredmeti.matematika ? false : true,
      };
    });
  };

  const engleskiHandler = (event) => {
    setPredmeti((prevPredmeti) => {
      return {
        ...prevPredmeti,
        engleski: prevPredmeti.engleski ? false : true,
      };
    });
  };

  const handleChangeFirstName = (e) => {
    setUpisanoIme(e.target.value);
  };
  const handleChangeAdress = (e) => {
    setAdresa(e.target.value);
  };
  const handleChangeOpis = (e) => {
    setOpis(e.target.value);
  };

  const handleChangeLastName = (e) => {
    setPrezime(e.target.value);
  };

  const handleChangeEmail = (e) => {
    setEmail(e.target.value);
  };

  const handleChangePhone = (e) => {
    setBroj(e.target.value);
  };

  const formCancelHandler = () => {
    window.location.href = "/profile";
  };

  const formUpdateHandler = (e) => {
    e.preventDefault();
    const promjene = {
      firstName: upisanoIme,
      lastName: upisanoPrezime,
      mobileNum: upisaniBroj,
      adress: upisanaAdresa,
      opis: upisanOpis,
      predajePredmete: [],
    };
    let popisPredmeta = [];
    if (predmetiCheckbox.matematika) popisPredmeta.push("matematika");
    if (predmetiCheckbox.fizika) popisPredmeta.push("fizika");
    if (predmetiCheckbox.hrvatski) popisPredmeta.push("hrvatski");
    if (predmetiCheckbox.engleski) popisPredmeta.push("engleski");
    promjene.predajePredmete = [...popisPredmeta];
    console.log(promjene);
    console.log(promjene.predajePredmete);

    let currentUser = AuthService.getCurrentUser();
    sendEditedData(
      currentUser.id,
      promjene.firstName,
      promjene.lastName,
      promjene.mobileNum,
      promjene.adress,
      promjene.opis,
      promjene.predajePredmete
    );
    window.location.href = "/profile";
  };

  const element = props;
  return (
    <>
      <Particles
        options={{
          particles: {
            color: {
              value: "#fff",
            },
            number: {
              value: 100,
            },
            opacity: {
              value: { min: 0.3, max: 1 },
            },
            shape: {
              type: "circle",
            },
            size: {
              value: { min: 1, max: 5 },
            },
            move: {
              direction: "bottom-right",
              enable: true,
              speed: { min: 3, max: 5 },
              straight: true,
            },
          },
        }}
        init={init}
      />
      <form onSubmit={formUpdateHandler}>
        <div class="container">
          <div class="row gutters">
            <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
              <div class="card h-100">
                <div class="card-body">
                  <div class="account-settings">
                    <div class="user-profile">
                      <h5 class="user-name">{element.firstName} </h5>
                      <h6 class="user-email">{element.email}</h6>
                    </div>
                    <div class="about">
                      <h5>Opis</h5>
                      <p>{element.opis}</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
              <div class="card h-100">
                <div class="card-body">
                  <div class="row gutters">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                      <h6 class="mb-2 text-primary">Osobni podatci</h6>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div class="form-group">
                        <label for="firstName">Ime</label>
                        <input
                          type="text"
                          class="form-control"
                          id="firstName"
                          defaultValue={props.firstName}
                          onChange={handleChangeFirstName}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label htmlFor="lastName">Prezime</label>
                        <input
                          type="text"
                          className="form-control"
                          id="lastName"
                          placeholder="prezime"
                          defaultValue={element.lastName}
                          onChange={handleChangeLastName}
                        ></input>
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label htmlFor="Street">Adresa</label>
                        <input
                          type="name"
                          className="form-control"
                          id="Street"
                          placeholder="Enter Street"
                          defaultValue={element.adresa}
                          onChange={handleChangeAdress}
                        ></input>
                      </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div class="form-group">
                        <label for="phone">Phone</label>
                        <input
                          type="text"
                          class="form-control"
                          id="phone"
                          placeholder="Enter phone number"
                          defaultValue={element.mobileNum}
                          onChange={handleChangePhone}
                        ></input>
                      </div>
                    </div>
                  </div>
                  <div class="row gutters">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                      <h6 class="mt-3 mb-2 text-primary">Adresa</h6>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div class="form-group">
                        <label for="opis">Opis</label>
                        <textarea
                          type="text"
                          class="form-control"
                          id="opis"
                          placeholder="Upiši opis"
                          defaultValue={element.opis}
                          onChange={handleChangeOpis}
                        ></textarea>
                      </div>
                    </div>
                    <div className="row gutters">
                      <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <h6 className="mt-3 mb-2 text-primary">
                          Odabir Predmeta
                        </h6>
                        <label className="container">
                          Matematika
                          <input
                            type="checkbox"
                            checked={predmetiCheckbox.matematika}
                            onChange={matematikaHandler}
                          />
                          <span className="checkmark"></span>
                        </label>
                        <label className="container">
                          Fizika
                          <input
                            type="checkbox"
                            checked={predmetiCheckbox.fizika}
                            onChange={fizikaHandler}
                          />
                          <span className="checkmark"></span>
                        </label>
                        <label className="container">
                          Hrvatski
                          <input
                            type="checkbox"
                            checked={predmetiCheckbox.hrvatski}
                            onChange={hrvatskiHandler}
                          />
                          <span className="checkmark"></span>
                        </label>
                        <label className="container">
                          Engleski
                          <input
                            type="checkbox"
                            checked={predmetiCheckbox.engleski}
                            onChange={engleskiHandler}
                          />
                          <span className="checkmark"></span>
                        </label>
                      </div>
                    </div>
                  </div>
                  <div class="row gutters">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                      <div class="text-right">
                        <button
                          type="button"
                          id="submit"
                          name="submit"
                          class="btn btn-secondary"
                          onClick={formCancelHandler}
                        >
                          Poništi
                        </button>
                        <button
                          type="submit"
                          id="submit"
                          name="submit"
                          class="btn btn-primary"
                        >
                          Ažuriraj
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>
    </>
  );
};

export default class EditProfile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: "",
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
      return <EditProfileView {...this.state.user} />;
    }
  }
}
