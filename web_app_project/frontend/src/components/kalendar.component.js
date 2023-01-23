import React, { Component, useState } from "react";
import { Navigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import {
  Inject,
  ScheduleComponent,
  Day,
  Week,
  WorkWeek,
  Month,
  Agenda,
  ViewsDirective,
  ViewDirective,
} from "@syncfusion/ej2-react-schedule";
import "../css/kalendar.css";
import customData from "../kalendar-resources/localeKalendar.json";
import 'react-datepicker/dist/react-datepicker.css'
import { getMyCalendar, sendNewTermin} from "../services/instruktor.service";
import { L10n, loadCldr } from "@syncfusion/ej2-base";


loadCldr(
  require("cldr-data/supplemental/numberingSystems.json"),
  require("cldr-data/main/hr/ca-gregorian.json"),
  require("cldr-data/main/hr/numbers.json"),
  require("cldr-data/main/hr/timeZoneNames.json")
);

L10n.load(customData);

const InstructorCalendarView = (props) => {
  const element = props;
  const dataSource = element.dataSource;
  // const localData = {
  //    dataSource: [
  //      {
  //        endTime: new Date(2022, 12, 19, 18, 30),
  //        startTime: new Date(2022, 12, 19, 16, 0),
  //        IsReadonly: true
  //      },
  //      {
  //        endTime: new Date(2022, 12, 20, 8, 30),
  //        startTime: new Date(2022, 12, 20, 6, 0),
  //        IsReadonly: true
  //      },
  //   ],
  //   fields: {
  //     subject: { name: "Summary", default: "Dostupan" },
  //     startTime: { name: "startTime" },
  //     endTime: { name: "endTime" },
  //   },
  // };

  const localDataNew = {
    dataSource,
    fields: {
      subject: { name: "Summary", default: "Dostupan" },
      startTime: { name: "startTime" },
      endTime: { name: "endTime" },
    },
  };

  function onFormSubmit(e){
    e.preventDefault();
    console.log(this.state.startDate);
  }
  const [upisanaGodina, setUpisanaGodina] = useState("2023");
  const [upisaniMjesec, setUpisaniMjesec] = useState("1");
  const [upisaniDan, setUpisaniDan] = useState("20");
  const [upisaniPocSat, setUpisaniPocSat] = useState("13");
  const [upisaniPocMin, setUpisaniPocMin] = useState("30");
  const [upisaniKrajSat, setUpisaniKrajSat] = useState("15");
  const [upisaniKrajMin, setUpisaniKrajMin] = useState("30");

  const handleChangeYear = (e) => {
    setUpisanaGodina(e.target.value);
  };

  const handleChangeMonth = (e) => {
    setUpisaniMjesec(e.target.value);
  };

  const handleChangeDay = (e) => {
    setUpisaniDan(e.target.value);
  };

  const handleChangeStartHour = (e) => {
    setUpisaniPocSat(e.target.value);
  };

  const handleChangeStartMin = (e) => {
    setUpisaniPocMin(e.target.value);
  };

  const handleChangeEndHour = (e) => {
    setUpisaniKrajSat(e.target.value);
  };

  const handleChangeEndMin = (e) => {
    setUpisaniKrajMin(e.target.value);
  };


  const formAddTermin = (e) => {
    e.preventDefault();
    const promjene = {
      year: upisanaGodina,
      month: upisaniMjesec,
      day: upisaniDan,
      startHour: upisaniPocSat,
      startMinute: upisaniPocMin,
      endHour: upisaniKrajSat,
      endMinute: upisaniKrajMin
    };
  
    let currentUser = AuthService.getCurrentUser();

    sendNewTermin(
      promjene.year,
      promjene.month,
      promjene.day,
      promjene.startHour,
      promjene.startMinute,
      promjene.endHour,
      promjene.endMinute
    );
    
     window.location.href = "/kalendar";
  };

  function onPopupOpen(args) {
    args.cancel = true;
  };


  return (
           <div className="container-whole-page">
             <div className="scheduler-container">
           <ScheduleComponent
             currentView="Week"
             selectedDate={new Date()}
             locale="hr"
             timeFormat="HH:mm"
             firstDayOfWeek={1}
             eventSettings={localDataNew}
             popupOpen={onPopupOpen}
           >
             <ViewsDirective>
               <ViewDirective
                 option="Day"
                 startHour="06:00"
                 endHour="23:00"
                 displayName="Dan"
               ></ViewDirective>
               <ViewDirective
                 option="Week"
                 startHour="06:00"
                 endHour="23:00"
                 displayName="Tjedan"
               ></ViewDirective>
               <ViewDirective
                 option="workWeek"
                 startHour="06:00"
                 endHour="23:00"
                 displayName="Radni tjedan"
               ></ViewDirective>
               <ViewDirective
                 option="Month"
                 showWeekNumber={true}
                 displayName="Mjesec"
               ></ViewDirective>
               <ViewDirective
                 option="Agenda"
                 displayName="Tvoji termini"
               ></ViewDirective>
             </ViewsDirective>
             <Inject services={[Day, Week, WorkWeek, Month, Agenda]} />
           </ScheduleComponent>
           </div>
           <form onSubmit={formAddTermin}>
              <div className="container-form-termin">
                <h4 className="form-title">Dodavanje termina</h4>
              <div class="form-group">
                <label for="year">Godina</label>
                <input
                  type="text"
                  class="form-control"
                  id="year"
                  placeholder="Unesite godinu"
                  defaultValue={"2023"}
                  onChange={handleChangeYear}
                />
                </div>
                <div className="form-group">
                  <label htmlFor="month">Mjesec</label>
                  <input
                    type="text"
                    className="form-control"
                    id="month"
                    placeholder="Unesite mjesec"
                    defaultValue={"1"}
                    onChange={handleChangeMonth}
                  ></input>
                </div>
                <div className="form-group">
                  <label htmlFor="day">Dan</label>
                  <input
                    type="text"
                    className="form-control"
                    id="day"
                    placeholder="Unesite dan"
                    defaultValue={"20"}
                    onChange={handleChangeDay}
                  ></input>
                </div>
                <div className="form-group">
                  <label htmlFor="startHour">Sat početka</label>
                  <input
                    type="text"
                    className="form-control"
                    id="startHour"
                    placeholder="Unesite početak u satima"
                    defaultValue={"13"}
                    onChange={handleChangeStartHour}
                  ></input>
                </div>
                <div className="form-group">
                  <label htmlFor="startMin">Minute početka</label>
                  <input
                    type="text"
                    className="form-control"
                    id="startMin"
                    placeholder="Unesite početak u minutama"
                    defaultValue={"30"}
                    onChange={handleChangeStartMin}
                  ></input>
                </div>
                <div className="form-group">
                  <label htmlFor="endHour">Sat završetka</label>
                  <input
                    type="text"
                    className="form-control"
                    id="endHour"
                    placeholder="Unesite kraj u satima"
                    defaultValue={"15"}
                    onChange={handleChangeEndHour}
                  ></input>
                </div>
                <div className="form-group">
                  <label htmlFor="endMin">Minute završetka</label>
                  <input
                    type="text"
                    className="form-control"
                    id="endMin"
                    placeholder="Unesite kraj u minutama"
                    defaultValue={"30"}
                    onChange={handleChangeEndMin}
                  ></input>
                </div>
                <button
                  type="submit"
                  id="submit"
                  name="submit"
                  class="btn btn-primary"
                >
                  Dodaj
                </button>
                </div>
           </form>
           </div>
         );
};

export default class Kalendar extends Component {
  constructor(props) {
    super(props);

    this.state = {
      startDate: new Date(),
      redirect: null,
      userReady: false,
    };
  
  }

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();
    if (!currentUser) {
        this.setState({ redirect: "/home" });
      } else {
        getMyCalendar().then(
            (response) => {
              this.setState({
                foundTermini: response.data,
                notFound: false,
              });
            },
            (error) => {
              this.setState({
                notFound: true,
              });
            }
          );
      }
    this.setState({ currentUser: currentUser, userReady: true });
  }

  render() {
    if (this.state.redirect) {
      return <Navigate to={this.state.redirect} />;
    } else if (this.state.notFound) {
      return <Navigate to={"/instruktori"} />;
    } else {
      return <InstructorCalendarView {...this.state.foundTermini} />;
    }
  }
}
