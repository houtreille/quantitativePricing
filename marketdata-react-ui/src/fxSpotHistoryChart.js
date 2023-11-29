import React, { Component } from 'react';
import {Line} from 'react-chartjs-2';
import Chart from 'chart.js/auto';
import FxSpotList from "./FxSpotList";
import data from "bootstrap/js/src/dom/data";



 class FxSpotHistoryChart extends Component {


     constructor(props) {
         super(props);
        // this.state = {fxSpots: []};
         this.state = {
             data: {
                 labels: [], // Tableau pour stocker les dates
                 datasets: [
                     {
                        data: [], // Tableau pour stocker les valeurs
                     },
                 ],
             },
         };
     }


     componentDidMount() {

         fetch('/fxSpot')
             .then((response) => response.json())
             .then((jsonData) => {

                 const dates = jsonData.map((data) => data.date);
                 const values = jsonData.map((data) => data.value);

                 // Mettre à jour le state avec les nouvelles données
                 this.setState((prevState) => ({
                     data: {
                         ...prevState.data,
                         labels: dates,
                         datasets: [
                             {
                                 ...prevState.data.datasets[0],
                                 data: values,
                             },
                         ],
                     },
                 }));
             })
             .catch((error) => {
                 // Gérer les erreurs de requête
                 console.error('Erreur lors de la récupération des données :', error);
             });
     }

    render() {
        return (
            <div>
                <Line data={this.state.data}/>
            </div>
        );
    }
}

export default FxSpotHistoryChart;