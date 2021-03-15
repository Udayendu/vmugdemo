def val = ''
if (params.Datacenter == 'PRIMARY-DC') {
val = 'Primary-DC-Agent'
} else if (params.Datacenter == 'SECONDARY-DC') {
val = 'Secondary-DC-Agent'
} 

pipeline {
agent {
	label val
}
stages {
    stage('Set parameters') {
        steps {
            script {
                currentBuild.displayName = '#' + currentBuild.number +
                                            '-' + params.Datacenter
                properties([
                    parameters([
                        [
                            $class: 'ChoiceParameter', 
                            choiceType: 'PT_SINGLE_SELECT', 
                            description: '', 
                            filterLength: 1, 
                            filterable: false, 
                            name: 'Environment', 
                            randomName: 'choice-parameter-4317672109824', 
                            script: [$class: 'GroovyScript', 
                                fallbackScript: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: ''
                                ], 
                                script: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: 'return [\'Select an Environment\',\'PROD\', \'DEV\']'
                                ]
                            ]
                        ],
                        [
                            $class: 'CascadeChoiceParameter', 
                            choiceType: 'PT_SINGLE_SELECT', 
                            description: '', 
                            filterLength: 1, 
                            filterable: false, 
                            name: 'Datacenter', 
                            randomName: 'choice-parameter-1434774074254', 
                            referencedParameters: 'Environment', 
                            script: [
                                $class: 'GroovyScript', 
                                fallbackScript: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: ''
                                ], script: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: '''
                                        def choices
                                        switch(Environment){
                                            case \'PROD\':
                                                choices = [\'Select a Datacenter\', \'PRIMARY-DC\', \'SECONDARY-DC\']
                                                break
                                            case \'DEV\':
                                                choices = [\'Select a Datacenter\', \'PRIMARY-DC\', \'SECONDARY-DC\']
                                                break
                                            default:
                                                choices = [\'N/A\']
                                                break
                                        }
                                        return choices'''
                                ]
                            ]
                        ],
                        [
                            $class: 'CascadeChoiceParameter', 
                            choiceType: 'PT_SINGLE_SELECT', 
                            description: '', 
                            filterLength: 1, 
                            filterable: false, 
                            name: 'Provision_Server', 
                            randomName: 'choice-parameter-143132192342342254', 
                            referencedParameters: 'Datacenter', 
                            script: [
                                $class: 'GroovyScript', 
                                fallbackScript: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: ''
                                ], script: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: '''
                                        def choices
                                        if (Datacenter=='PRIMARY-DC') {
                                        	choices=['Primary-DC-Agent']
                                        } else if (Datacenter=='SECONDARY-DC') {
                                            choices=['Secondary-DC-Agent']
                                        } else {
                                            choices=['None']
                                        }
                                        return choices'''
                                ]
                            ]
                        ],
                        [
                            $class: 'CascadeChoiceParameter', 
                            choiceType: 'PT_SINGLE_SELECT', 
                            description: '', 
                            filterLength: 1, 
                            filterable: false, 
                            name: 'vCenter_URL', 
                            randomName: 'choice-parameter-1431321902342254', 
                            referencedParameters: 'Datacenter', 
                            script: [
                                $class: 'GroovyScript', 
                                fallbackScript: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: ''
                                ], script: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: '''
                                        def choices
                                        if (Datacenter=='PRIMARY-DC') {
                                        	choices=['https://vmugdemo160.example.com/ui/']
                                        }
                                        else if (Datacenter=='SECONDARY-DC') {
                                            choices=['https://vmugdemo161.example.com/ui/']
                                        } else {
                                            choices=['None']
                                        }
                                        return choices'''
                                ]
                            ]
                        ],
                        [
                            $class: 'CascadeChoiceParameter', 
                            choiceType: 'PT_SINGLE_SELECT', 
                            description: '', 
                            filterLength: 1, 
                            filterable: false, 
                            name: 'vSphere_Cluster', 
                            randomName: 'choice-parameter-143477402342254', 
                            referencedParameters: 'Datacenter', 
                            script: [
                                $class: 'GroovyScript', 
                                fallbackScript: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: ''
                                ], script: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: '''
                                        def choices
                                        if (Datacenter=='PRIMARY-DC') {
                                            choices=['PRIMARY-CL']
                                        } else if (Datacenter=='SECONDARY-DC') {
                                            choices=['SECONDARY-CL']
                                        } else {
                                            choices=['None']
                                        }
                                        return choices'''
                                ]
                            ]
                        ],
                        [
                            $class: 'CascadeChoiceParameter', 
                            choiceType: 'PT_SINGLE_SELECT', 
                            description: '', 
                            filterLength: 1, 
                            filterable: false, 
                            name: 'vSphere_Folder', 
                            randomName: 'choice-parameter-143486474553365330', 
                            referencedParameters: 'Datacenter', 
                            script: [
                                $class: 'GroovyScript', 
                                fallbackScript: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: ''
                                ], script: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: '''
                                        def choices
                                        if (Datacenter=='PRIMARY-DC') {
                                            choices=['None','PRIMARY-MGMT-VMs', 'PRIMARY-PROD-VMs']
                                        } else if (Datacenter=='SECONDARY-DC') {
                                            choices=['None','SECONDARY-MGMT-VMs', 'SECONDARY-PROD-VMs']
                                        } else {
                                            choices=['None']
                                        }
                                        return choices'''
                                ]
                            ]
                        ],
                        [
                            $class: 'CascadeChoiceParameter', 
                            choiceType: 'PT_SINGLE_SELECT', 
                            description: '', 
                            filterLength: 1, 
                            filterable: false, 
                            name: 'Cluster_Level_Folder', 
                            randomName: 'choice-parameter-143486474553365329', 
                            referencedParameters: 'Datacenter', 
                            script: [
                                $class: 'GroovyScript', 
                                fallbackScript: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: ''
                                ], script: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: '''
                                        def choices
                                        if (Datacenter=='PRIMARY-DC') {
                                            choices=['None','PRIMARY-CL']
                                        } else if (Datacenter=='SECONDARY-DC') {
                                            choices=['None','SECONDARY-CL']
                                        } else {
                                            choices=['None']
                                        }
                                        return choices'''
                                ]
                            ]
                        ],
                        [
                            $class: 'CascadeChoiceParameter', 
                            choiceType: 'PT_SINGLE_SELECT', 
                            description: '', 
                            filterLength: 1, 
                            filterable: false, 
                            name: 'vSphere_Datastore', 
                            randomName: 'choice-parameter-143477402365329', 
                            referencedParameters: 'Datacenter, Customer_Number', 
                            script: [
                                $class: 'GroovyScript', 
                                fallbackScript: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: ''
                                ], script: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: '''
                                        def choices
                                        if (Datacenter=='PRIMARY-DC') {
                                            choices=['datastore1']
                                        } else if (Datacenter=='SECONDARY-DC') {
                                            choices=['datastore1']
                                        } else {
                                            choices=['None']
                                        }
                                        return choices'''
                                ]
                            ]
                        ],
                        [
                            $class: 'CascadeChoiceParameter', 
                            choiceType: 'PT_SINGLE_SELECT', 
                            description: '', 
                            filterLength: 1, 
                            filterable: false, 
                            name: 'vSphere_Datacenter', 
                            randomName: 'choice-parameter-1434323202365329', 
                            referencedParameters: 'Environment, Datacenter', 
                            script: [
                                $class: 'GroovyScript', 
                                fallbackScript: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: ''
                                ], script: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: '''
                                        def choices
                                        if (Environment=='PROD') {
                                            choices=['PRIMARY-DC','SECONDARY-DC']
                                        } else if (Datacenter=='DEV') {
                                            choices=['PRIMARY-DC','SECONDARY-DC']
                                        } else {
                                            choices=['None']
                                        }
                                        return choices'''
                                ]
                            ]
                        ],
                        [
                            $class: 'CascadeChoiceParameter', 
                            choiceType: 'PT_SINGLE_SELECT', 
                            description: '', 
                            filterLength: 1, 
                            filterable: false, 
                            name: 'Network_Subnet', 
                            randomName: 'choice-parameter-1434774q21314254', 
                            referencedParameters: 'Customer_Number, Datacenter, Environment', 
                            script: [
                                $class: 'GroovyScript', 
                                fallbackScript: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: ''
                                ], script: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: '''
                                        def choices
                                        if (Environment=='PROD') {
                                            choices=['None', 'VM Network']
                                        } else if (Environment=='Dev') {
                                            choices=['None', 'VM Network']
                                        } else {
                                            choices=['None']
                                        }
                                        return choices'''
                                    ]
                                ]
                            ],
                        string(
                            defaultValue: '255.255.255.0', 
                            description: '', 
                            name: 'Subnet_Netmask', 
                            trim: true
                        ),
                        string(
                            defaultValue: '', 
                            description: '', 
                            name: 'Default_Gateway', 
                            trim: true
                        ),
                        choice(
                            choices: [
                                'centos78'
                            ], 
                            description: '', 
                            name: 'Template'
                        ),
                        string(
                            defaultValue: '', 
                            description: '', 
                            name: 'DNS_Server1', 
                            trim: true
                        ),
                        string(
                            defaultValue: '', 
                            description: '', 
                            name: 'DNS_Server2', 
                            trim: true
                        ),
                        string(
                            defaultValue: 'dbbackup', 
                            description: '', 
                            name: 'Linux_User', 
                            trim: true
                        ),
                        password( 
                            description: 'The password must be at least 8 characters and must contain a lowercase, uppercase, special character and number.', 
                            name: 'Linux_User_Password'
                        ),
                        string(
                            defaultValue: '', 
                            description: '', 
                            name: 'Linux_IP', 
                            trim: true
                        ),
                        string(
                            defaultValue: '', 
                            description: '', 
                            name: 'Linux_Hostname', 
                            trim: true
                        ),
                        [
                            $class: 'CascadeChoiceParameter', 
                            choiceType: 'PT_SINGLE_SELECT', 
                            description: '', 
                            filterLength: 1, 
                            filterable: false, 
                            name: 'Timezone', 
                            randomName: 'choice-parameter-435671232309824',
                            referencedParameters: 'Datacenter', 
                            script: [$class: 'GroovyScript', 
                                fallbackScript: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script: ''
                                ], 
                                script: [
                                    classpath: [], 
                                    sandbox: true, 
                                    script:  '''
                                        def choices
                                        if (Datacenter=='PRIMARY-DC') {
                                            choices=['Asia/Kolkata']
                                        } else if (Datacenter=='SECONDARY-DC') {
                                            choices=['Asia/Kolkata']
                                        } else {
                                            choices=['None']
                                        }
                                        return choices'''
                                ]
                            ]
                        ]
                    ])
                ])
            }
        }
    }

    stage('Clone a Git Repository') {
    	steps {
    		script {
    			git credentialsId: 'git-credentials', url: getGitUrl()
    		}
    	}
    }

    stage ('Replace values in inventory file') {
    	steps {
    		withCredentials([
    			[$class: 'UsernamePasswordMultiBinding', credentialsId: getCredentialsId(),
                usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD'],
                [$class: 'UsernamePasswordMultiBinding', credentialsId: 'linuxos_credentials',
                usernameVariable: 'linuxos_username', passwordVariable: 'linuxos_userpassword']]) {
    		sh '''
host=$Linux_Hostname
ip=$Linux_IP
vmname=${host}_${ip}
pwd
vmFolder=''
if [ "$Environment" = "DEV" ] && [ "$Cluster_Level_Folder" != "None" ]
then
    vmFolder=/"$vSphere_Datacenter"/vm/"$vSphere_Cluster"/"$vSphere_Folder"
else
    vmFolder=/"$vSphere_Datacenter"/vm/"$vSphere_Folder"
fi
cat >inventory/dynamic_inventory << EOL
[linbackup]
$vmname

[golinbackup]
$ip

[golinbackup:vars]
ansible_connection=ssh
ansible_user=$linuxos_username
ansible_python_interpreter=/usr/bin/python2.7
ansible_password=$linuxos_userpassword
ansible_ssh_common_args='-o StrictHostKeyChecking=no'
EOL

cat >inventory/group_vars/all.yaml << EOL
# vCenter Login Information
vcenter_hostname: '$vCenter_URL'
vcenter_username: '$USERNAME'
vcenter_password: '$PASSWORD'

# Variable entries for windows virtual machine
vsphere_datacenter: '$vSphere_Datacenter'
vsphere_folder: '$vSphere_Folder'
folder: '$vmFolder'
vsphere_cluster: '$vSphere_Cluster'
vsphere_datastore: '$vSphere_Datastore'

# VM Network Components
Mgmt_network: '$Network_Subnet'
Mgmt_network_ipv4: '$Linux_IP'
vsphere_vm_hostname: '$Linux_Hostname'
Mgmt_network_nmv4: '255.255.255.0'
Mgmt_network_gwv4: '$Default_Gateway'

# Tempalate of OEL Linux for Backup Server
lin_temp: 'centos78'

# Timezone and NTP Server
# Use below command on a linux system to get timezone:
## timedatectl list-timezones
timezone: '$Timezone'
ntp_server:
  - '$DNS_Server1'
  - '$DNS_Server2'

# DNS server details
dns_server1: '$DNS_Server1'
dns_server2: '$DNS_Server2'
dns_domain: 'example.com'

# Backup volume size in GiB
size_gb: 50

# aocbackup user's password
backup_user: '$Linux_User'
dbbackup_password: '$Linux_User_Password'
'''
    		}
    	}
    }

    stage('Server Deployment') {
    	steps {
    		echo 'Starting the Linux Server deployment....'
            sh """
            ansible-playbook -i inventory linwebvm.yaml --tags "deploy"
            """
    	}
    }

    stage('Guest OS Customization') {
    	steps {
    		echo 'Starting the guest os customization'
            sh """
            ansible-playbook -i inventory linwebvm.yaml --tags "oscustom"
            """
    	}
    }

    stage('Package Installation') {
        steps {
            echo 'Starting the package installation'
            sh """
            ansible-playbook -i inventory linwebvm.yaml --tags "package"
            """
        }
    }


}
}

String getCredentialsId() {
if (params.Environment == "PROD") {
    "vCenter-Login"
} else if (params.Environment == "DEV") {
    "vCenter-Login"
} else {
        "N/A"
    }
}


String getGitUrl() {
    if (params.Environment == "PROD") {
    "https://github.com/Udayendu/vmugdemo01.git"
} else if (params.Environment == "DEV") {
    "https://github.com/Udayendu/vmugdemo01.git"
} else {
        "N/A"
    }
}
