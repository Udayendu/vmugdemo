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
                                            choices=['PRIMARY-DC']
                                        } else if (Datacenter=='PROD') {
                                            choices=['SECONDARY-DC']
                                        } else if (Datacenter=='DEV') {
                                            choices=['PRIMARY-DC']
                                        } else if (Datacenter=='DEV') {
                                            choices=['SECONDARY-DC']
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
                                'W2K16-Jan2021-Cloud'
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
                            defaultValue: 'adadmin', 
                            description: '', 
                            name: 'WDC_User', 
                            trim: true
                        ),
                        password( 
                            description: '', 
                            name: 'WDC_User_Password'
                        ),
                        string(
                            defaultValue: '', 
                            description: '', 
                            name: 'Windows_IP', 
                            trim: true
                        ),
                        string(
                            defaultValue: '', 
                            description: '', 
                            name: 'Windows_Hostname', 
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
                                            choices=['Central Standard Time (020)']
                                        } else if (Datacenter=='SECONDARY-DC') {
                                            choices=['Central Standard Time (020)']
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
                git credentialsId: 'accp-automation', url: getGitUrl()
            }
        }
    }

    stage ('Replace values in inventory file') {
        steps {
            withCredentials([
                [$class: 'UsernamePasswordMultiBinding', credentialsId: getCredentialsId(),
                usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD'],
                [$class: 'UsernamePasswordMultiBinding', credentialsId: 'guestos_credentials',
                usernameVariable: 'guest_username', passwordVariable: 'guest_userpassword']]) {
            sh '''
tz='020'
username='democloud\\'$WDC_User
ad_domain_admin='democloud\\demoadmin'
host=$Windows_Hostname
ip=$Windows_IP
vmname=${host}_${ip}
pwd
vmFolder=''
if [ "$Environment" = "PROD" ] && [ "$Cluster_Level_Folder" != "None" ]
then
    vmFolder=/"$vSphere_Datacenter"/vm/"$vSphere_Cluster"/"$vSphere_Folder"
else
    vmFolder=/"$vSphere_Datacenter"/vm/"$vSphere_Folder"
fi
cat >inventory/dynamic_inventory << EOL
[win-backup]
$vmname

[guestos-win-backup]
$ip

[guestos-win-backup:vars]
ansible_connection=winrm
ansible_user=$username
ansible_password=$WDC_User_Password
ansible_winrm_transport=credssp
ansible_winrm_server_cert_validation=ignore
ansible_port=5986
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
Mgmt_network_nmv4: '255.255.255.0'
Mgmt_network_gwv4: '$Default_Gateway'
Mgmt_network_ipv4: '$Windows_IP' 
vsphere_vm_hostname: '$Windows_Hostname'

# Tempalate of Windows 2016 template by demo IT
win_temp: 'W2K16-Jan2021-Cloud'

# Guest Credentials
guest_username: '$guest_username'
guest_userpassword: '$guest_userpassword'

### Variable file for Windows 2016 Guest OS ###

# Timezone and NTP Server
# For timezone codes: https://docs.microsoft.com/en-us/previous-versions/windows/embedded/ms912391(v=winembedded.11)?redirectedfrom=MSDN
timezone: '$tz'
ntp_server1: '$DNS_Server1'
ntp_server2: '$DNS_Server2'

# drive resizing details
partition_size: '-1'
disk_number1: '1'
disk_number2: '2'

# Disk size in GiB
ddrive_size: '500'
pdrive_size: '20'

# DNS server details
dns_server1: '$DNS_Server1'
dns_server2: '$DNS_Server2'

# Pagefile Size
initial_size: 20480
maximum_size: 20480

# AD details
ad_domain: 'democloud.com'
ad_domain_server: '$DNS_Server1'
ad_domain_admin: '$ad_domain_admin'
ad_domain_password: '$WDC_User_Password'
orgname: 'SELocalAdmin'

            '''
            }
        }
    }

    stage('VM Deployment') {
        steps {
            echo 'Starting the Windows Backup Server deployment....'
            sh """
            ansible-playbook -i inventory dcwindemo.yaml --tags "deploy"
            """
        }
    }

    stage('Guest Os Customization') {
        steps {
            echo 'Starting the guest os customization....'
            sh """
            ansible-playbook -i inventory dcwindemo.yaml --tags "guestoscustom"
            """
        }
    }    

    stage('Windows Update') {
        steps {
            echo 'Starting the windows update....'
            sh """
            ansible-playbook -i inventory dcwindemo.yaml --tags "winupdates"
            """
        }
    }

    stage('Software Upload') {
        steps {
            echo 'Starting the software upload'
            sh """
            ansible-playbook -i inventory dcwindemo.yaml --tags "softwareupload"
            """
        }
    }

    stage('Software Installation') {
        steps {
            echo 'Starting the software installation....'
            sh """
            ansible-playbook -i inventory dcwindemo.yaml --tags "softwareinstall"
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
    "https://github.com/Udayendu/vmugdemo02.git"
} else if (params.Environment == "DEV") {
    "https://github.com/Udayendu/vmugdemodev02.git"
} else {
        "N/A"
    }
}
