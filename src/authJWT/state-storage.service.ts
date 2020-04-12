import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class StateStorageService {

    previousState : { name: any, params: any } = null;
    destinationState : {
        destination: {
            name: any,
            data: any,
        },
        params: any,
        from: {
            name: any,
        }
    } = null;
    previousUrl : string = '';
    

    constructor() {}

    getPreviousState() {
        return this.previousState;
    }

    resetPreviousState() {
        this.previousState = null;
        this.destinationState = null;
        this.previousUrl = "";
    }

    storePreviousState(previousStateName, previousStateParams) {
        this.previousState = { name: previousStateName, params: previousStateParams };
    }

    getDestinationState() {
        return this.destinationState;
    }

    storeUrl(url: string) {
        this.previousUrl = url;
    }

    getUrl() {
        return this.previousUrl;
    }

    storeDestinationState(destinationState, destinationStateParams, fromState) {
        this.destinationState = {
            destination: {
                name: destinationState.name,
                data: destinationState.data
            },
            params: destinationStateParams,
            from: {
                name: fromState.name
            }
        };
    }
}
