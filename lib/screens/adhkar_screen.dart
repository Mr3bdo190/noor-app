import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class AdhkarScreen extends StatefulWidget {
  const AdhkarScreen({super.key});

  @override
  State<AdhkarScreen> createState() => _AdhkarScreenState();
}

class _AdhkarScreenState extends State<AdhkarScreen> {
  List<Map<String, dynamic>> _adhkar = [
    {'id': 'subhan', 'text': 'سُبْحَانَ اللَّهِ وَبِحَمْدِهِ', 'count': 33, 'total': 33},
    {'id': 'estghfar', 'text': 'أَسْتَغْفِرُ اللَّهَ وَأَتُوبُ إِلَيْهِ', 'count': 100, 'total': 100},
  ];

  @override
  void initState() {
    super.initState();
    _loadProgress();
  }

  _loadProgress() async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      for (var zikr in _adhkar) {
        zikr['count'] = prefs.getInt(zikr['id']) ?? zikr['total'];
      }
    });
  }

  _saveProgress(String id, int count) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setInt(id, count);
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      padding: const EdgeInsets.fromLTRB(20, 60, 20, 20),
      itemCount: _adhkar.length + 1,
      itemBuilder: (context, index) {
        if (index == 0) return const Padding(padding: EdgeInsets.only(bottom: 20), child: Text('الأذكار اليومية', style: TextStyle(fontSize: 28, color: Color(0xFF0F4C3A), fontWeight: FontWeight.bold)));
        final zikr = _adhkar[index - 1];
        final isDone = zikr['count'] == 0;

        return Card(
          color: isDone ? const Color(0xFF0F4C3A).withOpacity(0.05) : Colors.white,
          elevation: isDone ? 0 : 2,
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(15),
            side: BorderSide(color: isDone ? Colors.green : Colors.transparent),
          ),
          margin: const EdgeInsets.only(bottom: 15),
          child: ListTile(
            contentPadding: const EdgeInsets.all(15),
            onTap: isDone ? null : () {
              setState(() {
                zikr['count']--;
                _saveProgress(zikr['id'], zikr['count']);
              });
            },
            title: Text(zikr['text'], textAlign: TextAlign.center, style: TextStyle(fontSize: 20, color: isDone ? Colors.grey : const Color(0xFF0F4C3A), fontWeight: FontWeight.w600)),
            trailing: CircleAvatar(
              backgroundColor: isDone ? Colors.green : const Color(0xFFD4AF37),
              child: Text(isDone ? '✓' : '${zikr['count']}', style: const TextStyle(color: Colors.white, fontWeight: FontWeight.bold, fontSize: 18)),
            ),
          ),
        );
      },
    );
  }
}
