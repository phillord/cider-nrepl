#!/bin/bash

# runs source-deps and tests and then provided lein target with mranderson

function check_result {
    if [ $? -ne 0 ]; then
        echo "FAILED"
        exit 1
    fi
}

lein do clean, source-deps :project-prefix cider.inlined-deps
check_result
lein with-profile plugin.mranderson/config "$@"
