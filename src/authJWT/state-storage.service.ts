import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class StateStorageService {
    constructor() {}

    getPreviousState() {
        return sessionStorage.getItem('previousState');
    }

    resetPreviousState() {
        sessionStorage.clear();
    }

    storePreviousState(previousStateName, previousStateParams) {
        const previousState = { name: previousStateName, params: previousStateParams };
        sessionStorage.setItem('previousState', JSON.stringify(previousState));
    }

    getDestinationState() {
        return sessionStorage.getItem('destinationState');
    }

    storeUrl(url: string) {
        sessionStorage.setItem('previousUrl', url);
    }

    getUrl() {
        return sessionStorage.getItem('previousUrl');
    }

    storeDestinationState(destinationState, destinationStateParams, fromState) {
        const destinationInfo = {
            destination: {
                name: destinationState.name,
                data: destinationState.data
            },
            params: destinationStateParams,
            from: {
                name: fromState.name
            }
        };
        sessionStorage.setItem('destinationState', JSON.stringify(destinationInfo));
    }
}
